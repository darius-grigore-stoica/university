<?php
header("Content-Type: application/json");
$conn = new mysqli("localhost", "root", "", "licitatii_app");
if ($conn->connect_error) {
    echo json_encode(["success" => false, "message" => "Eroare conexiune DB"]);
    exit;
}

$data = json_decode(file_get_contents("php://input"), true);
$username = trim($data["username"] ?? "");
$parola = trim($data["parola"] ?? "");
$email = trim($data["email"] ?? "");
$telefon = trim($data["telefon"] ?? "");
$data_nastere = trim($data["data_nastere"] ?? "");
$rol = $data["rol"] ?? "cumparator";

if ($data_nastere === "") {
    $data_nastere = null;
}

$check = $conn->prepare("SELECT id FROM utilizatori WHERE username = ? OR email = ?");
$check->bind_param("ss", $username, $email);
$check->execute();
$check->store_result();

if ($check->num_rows > 0) {
    echo json_encode(["success" => false, "message" => "Username sau email deja folosit."]);
    exit;
}

$parola_hash = password_hash($parola, PASSWORD_DEFAULT);

$stmt = $conn->prepare(
    "INSERT INTO utilizatori (username, parola, rol, email, telefon, data_nastere)
     VALUES (?, ?, ?, ?, ?, ?)"
);
$stmt->bind_param("ssssss", $username, $parola_hash, $rol, $email, $telefon, $data_nastere);

if ($stmt->execute()) {
    echo json_encode(["success" => true, "message" => "Cont creat cu succes."]);
    header("Location: ../cont.php");
} else {
    echo json_encode([
        "success" => false,
        "message" => "Eroare la înregistrare: " . $stmt->error
    ]);
}

$stmt->close();
$check->close();
$conn->close();
?>