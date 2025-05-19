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
    <title>BuyNow! - Contul Meu</title>
    <link rel="stylesheet" href="styles/cont.css">
    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="scripts/cont.js"></script>
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
        <h1>Contul Meu</h1>
        <div class="cont-options">
            <div class="login-form">
                <h2>Autentificare</h2>
                <form method="POST" action="api/login.php">
                    <label for="username">Utilizator:</label>
                    <input type="text" id="username" name="username" required>
                    <label for="password">Parolă:</label>
                    <input type="password" id="password" name="password" required>
                    <button type="submit">Conectează-te</button>
                </form>

            </div>
            <div class="register-prompt">
                <p>Nu ai cont? <a href="inregistrare.php">Înregistrează-te</a></p>
            </div>
        </div>
    </main>
    <footer>
        <p>&copy; 2025 BuyNow!. Toate drepturile rezervate.</p>
    </footer>
</body>
</html>