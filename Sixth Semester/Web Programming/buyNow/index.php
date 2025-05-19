<?php
session_start();
$conn = new mysqli("localhost", "root", "", "licitatii_app");

if ($conn->connect_error) {
    die("Eroare conexiune DB");
}

$categorii = [];
$res = $conn->query("SELECT id, nume FROM categorii");
if ($res && $res->num_rows > 0) {
    while ($row = $res->fetch_assoc()) {
        $categorii[] = $row;
    }
}
?>

<!DOCTYPE html>
<html lang="ro">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BuyNow! - Acasă</title>
    <link rel="stylesheet" href="styles/index.css">
    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="styles/slider.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="scripts/index.js"></script>
    <script src="scripts/jQuery/slider.js"></script>
</head>
<body>
    <header>
        <div class="logo">
            <img src="logo.png" alt="Logo">
        </div>
        <nav class="main-nav">
            <ul>
                <li><a href="index.php"><i class="fas fa-home"></i> Acasă</a></li>
                <li><a href="favorite.php"><i class="fas fa-heart"></i> Favorite</a></li>
                <li id="li-anunturi"><a href="anunturi.php"><i class="fas fa-heart"></i> Anunturi</a></li>
                <li id="adauga-anunt-li"><a href="adauga_anunt.php"><i class="fa-solid fa-arrow-up-from-bracket"></i> Adaugă Anunt</a></li>
                <?php if (isset($_SESSION['user_id'])): ?>
                    <li><a href="api/logout.php"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                <?php else: ?>
                    <li><a href="cont.php"><i class="fa-solid fa-circle-user"></i> Cont</a></li>
                <?php endif; ?>

            </ul>
        </nav>
    </header>
    <nav class="vertical-menu">
        <h3><a href="anunturi.php">Anunțuri</a></h3>
        <ul>
            <?php foreach ($categorii as $cat): ?>
                <li>
                    <a href="anunturi.php?categorie=<?= $cat['id'] ?>">
                        <?= htmlspecialchars($cat['nume']) ?>
                    </a>
                </li>
            <?php endforeach; ?>
        </ul>
    </nav>
    <main>
        <section class="slider-section">
            <div class="slider-controls">
                <label for="slideDuration">Slide Duration:</label>
                <select id="slideDuration">
                    <option value="3000">3 sec</option>
                    <option value="5000" selected>5 sec</option>
                    <option value="8000">8 sec</option>
                </select>
        
                <select id="slidesPerView">
                    <option value="1">1 Slide</option>
                    <option value="2">2 Slides</option>
                    <option value="3" selected>3 Slides</option>
                </select>
            </div>
        
            <div class="slider">
                <div class="slide active" style="background-image: url('images/promo_1.jpg')">
                    <div class="caption">
                        <h2>REDUCERI DE PANA LA 50%</h2>
                        <p>Profita de reducerile la electrocasnice pana pe 1 iunie.</p>
                    </div>
                    <div class="slider-nav">
                        <button class="prev-slide">&#10094;</button>
                        <button class="next-slide">&#10095;</button>
                    </div>
                </div>
                <div class="slide" style="background-image: url('images/promo_2.jpg')">
                    <div class="caption">
                        <h2>PROMOTIA DE VARĂ!</h2>
                        <p>Completează-ti bucătăria cu cele mai noi electrocasnice. Acum la reducere!</p>
                    </div>
                    <div class="slider-nav">
                        <button class="prev-slide">&#10094;</button>
                        <button class="next-slide">&#10095;</button>
                    </div>
                </div>
                <!-- Video Slide (now with correct video-slide class) -->
                <div class="slide video-slide"> <!-- Added video-slide class here -->
                    <video class="slide-video" muted loop>
                        <source src="videos/freestock_4076443.mp4" type="video/mp4">
                    </video>
                    <div class="caption">
                        <h2>VIDEO</h2>
                        <p>Acest video este un demo.</p>
                    </div>
                    <div class="slider-nav">
                        <button class="prev-slide">&#10094;</button>
                        <button class="next-slide">&#10095;</button>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <footer>
        <p>&copy; 2025 BuyNow! Toate drepturile rezervate.</p>
    </footer>
</body>
</html>