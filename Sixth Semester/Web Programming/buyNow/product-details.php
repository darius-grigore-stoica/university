<?php
session_start();
$conn = new mysqli("localhost", "root", "", "licitatii_app");

if ($conn->connect_error) {
    die("Eroare conexiune DB");
}
// Categorii pentru meniu lateral
$categorii = [];
$res = $conn->query("SELECT id, nume FROM categorii");
if ($res && $res->num_rows > 0) {
    while ($row = $res->fetch_assoc()) {
        $categorii[] = $row;
    }
}

$id_anunt = isset($_GET['id']) ? intval($_GET['id']) : 0;
if ($id_anunt <= 0) {
    die("ID anunț invalid.");
}

$stmt = $conn->prepare("SELECT titlu, descriere, pret_actual, pret_initial, image_url FROM anunturi WHERE id = ?");
if (!$stmt) {
    die("Eroare SQL: " . $conn->error);
}

$stmt->bind_param("i", $id_anunt);
$stmt->execute();
$result = $stmt->get_result();
$anunt = $result->fetch_assoc();

if (!$anunt) {
    die("Anunțul nu a fost găsit.");
}
?>


<!DOCTYPE html>
<html lang="ro">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalii Produs - Licitație</title>
    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="styles/product-details.css">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
        <h1>Detalii Produs</h1>
        <div id="product-details">
            <h2><?= htmlspecialchars($anunt['titlu']) ?></h2>
            <img src="<?= htmlspecialchars($anunt['image_url']) ?>" alt="Imagine produs" style="width: 200px;">
            <p><?= htmlspecialchars($anunt['descriere']) ?></p>
            <p>Pret Corect: <strong><?= number_format($anunt['pret_initial'], 2) ?> RON</strong></p>
            <p>Pret Licitat: <strong><?= number_format($anunt['pret_actual'], 2) ?> RON</strong></p>

            <?php if (!isset($_SESSION['user_id'])): ?>
                <p>Trebuie să fii autentificat pentru a licita.</p>
            <?php else: ?>
                <form method="POST" action="api/update_bid.php">
                    <input type="hidden" name="id_anunt" value="<?= $id_anunt ?>">
                    <input type="number" name="bid" placeholder="Sumă ofertată" required>
                    <button type="submit">Plasează oferta</button>
                </form>
            <?php endif; ?>

            <?php if (isset($_GET['success'])): ?>
                <p style="color:green;">Ofertă înregistrată!</p>
            <?php elseif (isset($_GET['error'])): ?>
                <p style="color:red;"><?= htmlspecialchars($_GET['error']) ?></p>
            <?php endif; ?>
        </div>
    </main>


    <footer>
        <p>&copy; 2025 BuyNow!. Toate drepturile rezervate.</p>
    </footer>
    <script src="scripts/jQuery/product-details.js"></script>
</body>
</html>
