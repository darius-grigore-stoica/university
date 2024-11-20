<?php
$local_address = "172.17.104.133";
$local_port = 5556;

$remote_address = "172.17.96.1";
$remote_port = 5555;

$sock = socket_create(AF_INET, SOCK_DGRAM, 0);
socket_bind($sock, $local_address, $local_port);
socket_set_nonblock($sock);

// Login
echo "Let's login!\nUsername: ";
$username = trim(fgets(STDIN));
socket_sendto($sock, $username, strlen($username), 0, $remote_address, $remote_port);

echo "Password: ";

// Disable echo
shell_exec('stty -echo');
$password = trim(fgets(STDIN)); // Get password input
shell_exec('stty echo'); // Re-enable echo

echo "\n";

socket_sendto($sock, $password, strlen($password), 0, $remote_address, $remote_port);

$auth = ""; $from = ""; $port = 0;
socket_recvfrom($sock, $auth, 1024, 0, $from, $port);

$auth = "";
$from = "";
$port = 0;

// Receive authentication response with retry and timeout
$startTime = time();
$timeout = 10; // Timeout in seconds
while (empty($auth)) {
    $bytes = socket_recvfrom($sock, $auth, 1024, 0, $from, $port);
    if ($bytes > 0) {
        break; // Exit loop on successful receive
    }
    if ((time() - $startTime) > $timeout) {
        echo "Timeout waiting for authentication response.\n";
        exit(1);
    }
    usleep(100000); // Sleep for 100ms before retrying
}

// Trim and check authentication response
$auth = trim($auth);
if ($auth == "True") {
    echo "You're in. Let's chat!\n";

    $pid = pcntl_fork();
    if ($pid == -1) {
        die("Couldn't fork process\n");
    } elseif ($pid) {
        // Parent: Handle incoming messages
        while (true) {
            $buff = "";
            $from = "";
            $port = 0;
            $bytes = socket_recvfrom($sock, $buff, 1024, 0, $from, $port);
            if ($bytes > 0) {
                echo "$buff\n";
            }
            usleep(100000); // Sleep 100ms
        }
    } else {
        // Child: Handle sending messages
        while (true) {
            $message = trim(fgets(STDIN));
            if ($message !== '') {
                $timestamp = date("m-d H:i:");
                $full_message = "[PHP | $timestamp] $message";
                socket_sendto($sock, $full_message, strlen($full_message), 0, $remote_address, $remote_port);
            }
            usleep(100000); // Sleep 100ms
        }
    }
} else {
    echo "Your credentials were wrong\n";
}
?>
