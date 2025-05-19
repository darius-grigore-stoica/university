<?php
session_start();
if (!isset($_SESSION['user_id']) || $_SESSION['rol'] !== 'vanzator') {
    header("Location: cont.php"); // or an error page
    exit;
}

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
    <title>BuyNow! - Adaugă Anunț</title>
    <link rel="stylesheet" href="styles/adauga_anunt.css">
    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="scripts/adauga_anunt.js"></script>
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
        <h1>Adaugă Anunț</h1>
        <form id="adaugaAnuntForm" enctype="multipart/form-data">
            <label for="titlu">Poză:</label>
            <input type="file" id="poza" name="poza" required>
            <label for="titlu">Titlu:</label>
            <input type="text" id="titlu" name="titlu" required>
            <label for="descriere">Descriere:</label>
            <textarea id="descriere" name="descriere" required></textarea>
            <label for="pret">Preț de start:</label>
            <input type="number" id="pret" name="pret" required>
            <label for="categorie">Categorie:</label>
            <select id="categorie" name="categorie" required>
                <?php foreach ($categorii as $cat): ?>
                    <option value="<?= $cat['id'] ?>"><?= htmlspecialchars($cat['nume']) ?></option>
                <?php endforeach; ?>
            </select>

            <div class="location-filters">
                <div class="filter-row">
                    <div class="filter-group">
                        <label for="judet">Județ:</label>
                        <select id="judet" name="judet">
                            <option value="">Selectează județul</option>
                        </select>
                    </div>
                    <div class="filter-group">
                        <label for="oras">Oraș:</label>
                        <select id="oras" name="oras" disabled>
                            <option value="">Selectează județul mai întâi</option>
                        </select>
                    </div>
                </div>
            </div>
            <button type="submit">Adaugă Anunț</button>
        </form>
    </main>

    <footer>
        <p>&copy; 2025 BuyNow!. Toate drepturile rezervate.</p>
    </footer>
</body>
</html>