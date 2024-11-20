<?php
$host = '127.0.0.1';  // Server address
$port = 12345;        // Server port


$socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
$socket_connect($socket, $host, $port);

echo "Connected to server at $host:$port\n";

function print_board($board) {
    echo "\n  a b c d e f g h\n";
    echo " +----------------+\n";
    foreach ($board as $row_index => $row) {
        echo (8 - $row_index) . "| " . implode(' ', $row) . " |\n";
    }
    echo " +----------------+\n";
}

for ($i = 1; $i <= 10; $i++) {
    // Preluam miscarea clientului
    echo "Enter your move (e.g., e2e4): ";
    $client_move = trim(fgets(STDIN));

    // Trimitem miscare serverului pentru a fi validata
    socket_write($socket, $client_move, strlen($client_move));

    // Dupa validare, primit tabla cu miscarea facuta de server
    $response = socket_read($socket, 4096);

    // Decodam miscarea din format JSON
    $data = json_decode($response, true);

    if (isset($data['board']) && isset($data['server_move'])) {
        // Afisam tabla modificata si modificarea facuta de server
        echo "\nServer Move: " . $data['server_move'] . "\n";
        print_board($data['board']);
    } else {

        // Afisam un raspuns, in caz ca servul a returnat erori
        echo $response . "\n";
    }
}

echo "Game over. Disconnecting.\n";
socket_close($socket);
