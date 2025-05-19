<?php
session_start();
header("Content-Type: application/json");

$conn = new mysqli("localhost", "root", "", "licitatii_app");

if (!isset($_SESSION['user_id'])) {
    echo json_encode(["success" => false, "message" => "Neautentificat"]);
    exit;
}

$data = json_decode(file_get_contents("php://input"), true);
$id_utilizator = $_SESSION['user_id'];
$id_anunt = intval($data["id_anunt"] ?? 0);

$stmt = $conn->prepare("DELETE FROM favorite WHERE id_utilizator = ? AND id_anunt = ?");
$stmt->bind_param("ii", $id_utilizator, $id_anunt);

if ($stmt->execute()) {
    echo json_encode(["success" => true]);
} else {
    echo json_encode(["success" => false, "message" => "Eroare la ștergere"]);
}
