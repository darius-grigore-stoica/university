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
    <title>BuyNow! - Înregistrare</title>
    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="styles/inregistrare.css">
    <script src="scripts/inregistrare.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
                <li><a href="cont.php"><i class="fa-solid fa-circle-user"></i> Cont</a></li>
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
        <div class="register-container">
            <div class="register-form">
                <h1 class="form-title"><i class="fas fa-user-plus"></i> Înregistrare cont nou</h1>
                
                <form id ="register-form" method="POST" action="api/register.php">
                    <div class="form-group input-icon">
                        <label for="username">Nume utilizator:</label>
                        <i class="fas fa-user"></i>
                        <input type="text" id="username"  name="username" placeholder="exemplu123">
                        <div class="requirement" id="username-req">
                            <i class="far fa-circle"></i> Doar litere mici și cifre
                        </div>
                    </div>
                    
                    <div class="form-group input-icon">
                        <label for="email">Adresă email:</label>
                        <i class="fas fa-envelope"></i>
                        <input type="email" id="email" name="email" placeholder="exemplu@domeniu.com">
                    </div>
                    
                    <div class="form-group input-icon">
                        <label for="password">Parolă:</label>
                        <i class="fas fa-lock"></i>
                        <input type="password" id="password"name="parola" placeholder="Introdu o parolă sigură">
                        <div class="requirement" id="lower-req">
                            <i class="far fa-circle"></i> Cel puțin o literă mică
                        </div>
                        <div class="requirement" id="upper-req">
                            <i class="far fa-circle"></i> Cel puțin o literă mare
                        </div>
                        <div class="requirement" id="number-req">
                            <i class="far fa-circle"></i> Cel puțin o cifră
                        </div>
                        <div class="requirement" id="special-req">
                            <i class="far fa-circle"></i> Cel puțin caracterul !
                        </div>
                    </div>
                    
                    <div class="form-group input-icon">
                        <label for="confirmPassword">Confirmă parola:</label>
                        <i class="fas fa-lock"></i>
                        <input type="password" id="confirmPassword"  name="confirm_parola" placeholder="Introdu din nou parola">
                    </div>
                    
                    <div class="form-group input-icon">
                        <label for="phone">Număr de telefon:</label>
                        <i class="fas fa-phone"></i>
                        <input type="text" id="phone" name="telefon" placeholder="(+40) 777 777 777">
                    </div>

                    <div class="form-group date-group">
                        <label for="birthDate">Data nașterii:</label>

                        <div class="date-input-container">
                            <i class="fas fa-calendar-alt"></i>
                            <input type="text" id="birthDate" name="data_nastere" placeholder="zz/ll/aaaa">
                            <div class="date-format-selector">
                                <label for="dateFormat">Format:</label>
                                <select id="dateFormat">
                                    <option value="zz/ll/aaaa">zz/ll/aaaa</option>
                                    <option value="ll/zz/aaaa">ll/zz/aaaa</option>
                                    <option value="zz/ll/aa">zz/ll/aa</option>
                                </select>
                            </div>  
                        </div>
                        <div class="requirement" id="format-req" style="margin-top: 10px;">
                            <i class="far fa-circle"></i> Format Corect
                        </div>
                    </div>
                    <div class="form-group input-icon">
                        <label for="rol">Tip utilizator:</label>
                        <i class="fas fa-user-tag"></i>
                        <select id="rol">
                            <option value="cumparator">Cumpărător</option>
                            <option value="vanzator">Vânzător</option>
                        </select>
                    </div>
                    <button type="submit" class="register-btn">
                        <i class="fas fa-user-plus"></i> Înregistrează-te
                    </button>
                    <div class="login-link">
                        Ai deja cont? <a href="cont.php"><i class="fas fa-sign-in-alt"></i> Autentifică-te</a>
                    </div>
                </form>
                <?php if (isset($_GET['error'])): ?>
                    <p class="error-message"><?= htmlspecialchars($_GET['error']) ?></p>
                <?php elseif (isset($_GET['success'])): ?>
                    <p class="success-message">Cont creat cu succes! Autentifică-te.</p>
                <?php endif; ?>

            </div>
        </div>
    </main>
    <footer>
        <p>&copy; 2023 BuyNow!. Toate drepturile rezervate.</p>
    </footer>
</body>
</html>