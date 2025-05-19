<?php
session_start();
$conn = new mysqli("localhost", "root", "", "licitatii_app");

if ($conn->connect_error) {
    die("Eroare conexiune: " . $conn->connect_error);
}

$query = "SELECT * FROM anunturi";
$result = $conn->query($query);

$anunturi = [];
if ($result && $result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $anunturi[] = $row;
    }
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
    <title>BuyNow! - Anunțuri</title>
    <link rel="stylesheet" href="styles/anunturi.css">
    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="scripts/anunturi.js"></script>
    <script src="scripts/jQuery/search_bar.js"></script>
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
        <div class="header-with-search">
            <h1>Anunțuri Disponibile</h1>
            <div class="search-autocomplete">
                <div class="compact-search-container">
                    <div class="search-icon-container">
                        <i class="fas fa-search"></i>
                    </div>
                    <input type="text" id="searchInput" placeholder="Caută..." autocomplete="off">
                </div>
                <div id="searchSuggestions" class="search-suggestions"></div>
            </div>
        </div>
        <table class="anunturi-table sortable">
            <thead>
                <tr>
                    <th id="th-imagine">Imagine</th>
                    <th>Descriere <span class="sort-icon"></span></th>
                    <th>Detalii <span class="sort-icon"></span></th>
                    <th>Preț <span class="sort-icon"></span></th>
                    <th id="th-actiune">Acțiune</th>
                </tr>
            </thead>
            <tbody>
                 <?php if (empty($anunturi)): ?>
                <tr><td colspan="5">Nu există anunțuri disponibile.</td></tr>
                <?php else: ?>  
                    <?php foreach ($anunturi as $anunt): ?>
                        <tr>
                            <td>
                                <img src="<?= htmlspecialchars($anunt['image_url']) ?>" alt="imagine" width="100">
                            </td>
                            <td><?= htmlspecialchars($anunt['titlu']) ?></td>
                            <td><?= htmlspecialchars($anunt['descriere']) ?></td>
                            <td><?= number_format($anunt['pret_actual'], 2) ?> RON</td>
                            <td>
                                <a href="product-details.php?id=<?= $anunt['id'] ?>">
                                    <button>Licitează</button>
                                </a>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                <?php endif; ?>
            </tbody>
        </table>
    </main>
    <footer>
        <p>&copy; 2025 BuyNow!. Toate drepturile rezervate.</p>
    </footer>
</body>
</html>