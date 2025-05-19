<?php
session_start();
$conn = new mysqli("localhost", "root", "", "licitatii_app");

if ($conn->connect_error) {
    die("Eroare conexiune DB");
}

if (!isset($_SESSION['user_id'])) {
    header("Location: cont.php");
    exit;
}

$id_utilizator = $_SESSION['user_id'];

$categorii = [];
$res = $conn->query("SELECT id, nume FROM categorii");
if ($res && $res->num_rows > 0) {
    while ($row = $res->fetch_assoc()) {
        $categorii[] = $row;
    }
}

$stmt = $conn->prepare("
    SELECT a.id, a.titlu, a.descriere, a.pret_actual, a.image_url
    FROM favorite f
    JOIN anunturi a ON f.id_anunt = a.id
    WHERE f.id_utilizator = ?
");
$stmt->bind_param("i", $id_utilizator);
$stmt->execute();
$result = $stmt->get_result();

$favorites = [];
while ($row = $result->fetch_assoc()) {
    $favorites[] = $row;
}

?>

<!DOCTYPE html>
<html lang="ro">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BuyNow! - Favorite</title>
    <link rel="stylesheet" href="styles/favorite.css">
    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="scripts/favorite.js"></script>
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
        <h1>Anunțurile Mele Favorite</h1>
        <?php if (empty($favorites)): ?>
            <p>Nu ai anunțuri favorite momentan.</p>
        <?php else: ?>
            <?php foreach ($favorites as $anunt): ?>
                <div class="anunt">
                    <img src="<?= htmlspecialchars($anunt['image_url']) ?>" alt="img" width="120">
                    <h2><?= htmlspecialchars($anunt['titlu']) ?></h2>
                    <p><?= htmlspecialchars($anunt['descriere']) ?></p>
                    <p><strong>Preț:</strong> <?= number_format($anunt['pret_actual'], 2) ?> RON</p>

                    <form method="POST" action="favorite.php">
                        <input type="hidden" name="id_anunt" value="<?= $anunt['id'] ?>">
                        <button type="submit" onclick="stergeFavorite(<?= $anunt['id'] ?>)">Șterge din favorite</button>
                    </form>
                </div>
            <?php endforeach; ?>
        <?php endif; ?>
    </main>
    <footer>
        <p>&copy; 2025 BuyNow!. Toate drepturile rezervate.</p>
    </footer>
</body>
</html>