import socket
import threading
import datetime

# Globals (avoid hardcoding in production)
GLOBAL_USER = "darius"
GLOBAL_PASSWORD = "root"

local_address = ("172.17.96.1", 5555)
remote_address = ("172.17.104.133", 5556)

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(local_address)

running = True

def send_message():
    while running:
        message = input("")
        if message:
            timestamp = datetime.datetime.now().strftime("%m-%d %H:%M")
            full_message = f"[Python | {timestamp}] {message}"
            s.sendto(full_message.encode(), remote_address)

def receive_message():
    while running:
        try:
            data, address = s.recvfrom(1024)
            print(f"{data.decode()}")
        except socket.error as e:
            print(f"Socket error: {e}")
            continue  # Keep listening

def login():
    try:
        # Receive username
        data, _ = s.recvfrom(1024)
        username = data.decode()
        # Receive password
        data, _ = s.recvfrom(1024)
        password = data.decode()
        return username == GLOBAL_USER and password == GLOBAL_PASSWORD
    except Exception as e:
        print(f"Login error: {e}")
        return False

# Authentication
is_authenticated = login()

if is_authenticated:

    message="True"

    s.sendto(message.encode(), remote_address)
    print(f"The user from {remote_address} is in. Let's chat!")
    # Start threads
    send_thread = threading.Thread(target=send_message)
    receive_thread = threading.Thread(target=receive_message)

    send_thread.start()
    receive_thread.start()

    # Wait for threads to finish (optional)
    send_thread.join()
    receive_thread.join()

    # Close socket after threads terminate
    s.close()
else:
    failed="Failed"
    s.sendto(failed.encode(), remote_address)
    print("Authentication failed.")
