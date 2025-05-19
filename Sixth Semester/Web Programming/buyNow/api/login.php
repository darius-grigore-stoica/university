<?php
session_start();
$conn = new mysqli("localhost", "root", "", "licitatii_app");

$username = $_POST['username'] ?? '';
$password = $_POST['password'] ?? '';

$stmt = $conn->prepare("SELECT id, parola, rol FROM utilizatori WHERE username = ?");
$stmt->bind_param("s", $username);
$stmt->execute();
$result = $stmt->get_result();

if ($user = $result->fetch_assoc()) {
    if (password_verify($password, $user['parola'])) {
        $_SESSION['user_id'] = $user['id'];
        $_SESSION['rol'] = $user['rol'];
        $_SESSION['username'] = $username;

        if ($user['rol'] === 'vanzator') {
            header("Location: ../adauga_anunt.php");
        } else {
            header("Location: ../anunturi.php");
        }
        exit;
    }
}

header("Location: ../cont.php?error=Date incorecte");
