<?php
session_start();

$conn = new mysqli("localhost", "root", "", "licitatii_app");
if ($conn->connect_error) {
    die("Eroare conexiune DB");
}

// Verificăm dacă utilizatorul este autentificat
if (!isset($_SESSION['user_id'])) {
    header("Location: ../cont.php?error=Trebuie să fii autentificat pentru a licita.");
    exit;
}

// Verificăm dacă am primit datele necesare
$id_anunt = isset($_POST['id_anunt']) ? intval($_POST['id_anunt']) : 0;
$bid = isset($_POST['bid']) ? floatval($_POST['bid']) : 0;

if ($id_anunt <= 0 || $bid <= 0) {
    header("Location: ../product-details.php?id=$id_anunt&error=Date invalide.");
    exit;
}

// Verificăm prețul curent
$stmt = $conn->prepare("SELECT pret_actual FROM anunturi WHERE id = ?");
$stmt->bind_param("i", $id_anunt);
$stmt->execute();
$stmt->bind_result($pret_actual);
$stmt->fetch();
$stmt->close();

if ($bid <= $pret_actual) {
    header("Location: ../product-details.php?id=$id_anunt&error=Oferta trebuie să fie mai mare decât prețul actual.");
    exit;
}

// Actualizăm prețul
$update = $conn->prepare("UPDATE anunturi SET pret_actual = ? WHERE id = ?");
$update->bind_param("di", $bid, $id_anunt);

if ($update->execute()) {
    header("Location: ../product-details.php?id=$id_anunt&success=1");
} else {
    header("Location: ../product-details.php?id=$id_anunt&error=Eroare la actualizare.");
}
?>
