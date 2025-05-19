<?php
session_start();
header("Content-Type: application/json");

$conn = new mysqli("localhost", "root", "", "licitatii_app");
if ($conn->connect_error) {
    echo json_encode(["success" => false, "message" => "Eroare conexiune DB"]);
    exit;
}

if (!isset($_SESSION['user_id']) || $_SESSION['rol'] !== 'vanzator') {
    echo json_encode(["success" => false, "message" => "Doar vânzătorii pot adăuga anunțuri."]);
    exit;
}

$titlu = trim($_POST['titlu'] ?? "");
$descriere = trim($_POST['descriere'] ?? "");
$pret = floatval($_POST['pret'] ?? 0);
$categorie = intval($_POST['categorie'] ?? 0);
$data = date("Y-m-d H:i:s");

if (!$titlu || !$descriere || $pret <= 0 || $categorie <= 0 || !isset($_FILES['poza'])) {
    echo json_encode(["success" => false, "message" => "Completează toate câmpurile."]);
    exit;
}

$filename = basename($_FILES["poza"]["name"]);
$image_url = "images/" . $filename;

// Inserare în DB
$stmt = $conn->prepare("
    INSERT INTO anunturi (titlu, descriere, pret_initial, pret_actual, image_url, data_adaugare, id_categorie)
    VALUES (?, ?, ?, ?, ?, ?, ?)
");

$stmt->bind_param("ssddssi", $titlu, $descriere, $pret, $pret, $image_url, $data, $categorie);

if ($stmt->execute()) {
    echo json_encode(["success" => true, "message" => "Anunț adăugat cu succes."]);
} else {
    echo json_encode(["success" => false, "message" => "Eroare la inserare: " . $stmt->error]);
}
?>
