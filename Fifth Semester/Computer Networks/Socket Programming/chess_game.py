import socket
import chess
import json
import threading

HOST = '127.0.0.1'  # Localhost
PORT = 12345        # TCP Server Port


# Functie apelata pentru a gestiona fiecare client
def handle_client(client_socket, client_address):
    print(f"New connection from {client_address}")

    # Inializam o tabla de sah
    board = chess.Board()

    # Functie ce transforma o tabla intr-o matrice (lista 2D)
    def board_to_array(board):
        return [list(str(board).splitlines()[i]) for i in range(8)]

    try:
        for _ in range(10):  # Pentru simplitate, limitam jocul la 10 mutari
            # Primim miscarea clientului
            data = client_socket.recv(1024).decode().strip()
            print(f"Client {client_address} Move: {data}")

            try:
                # Modificam tabla cu miscarea primita de la client
                move = chess.Move.from_uci(data)
                if move in board.legal_moves:
                    board.push(move)
                else:
                    raise ValueError("Illegal move")
            except Exception as e:
                client_socket.send(f"Invalid move: {e}".encode())
                continue

            # Generam o miscare pentru server
            if not board.is_game_over():
                server_move = next(iter(board.legal_moves))
                board.push(server_move)
            else:
                client_socket.send("Game over!".encode())
                break

            # Trimitem tabla modificata catre client
            response = {
                "board": board_to_array(board),
                "server_move": server_move.uci()
            }
            client_socket.send(json.dumps(response).encode())
    except Exception as e:
        print(f"Error handling client {client_address}: {e}")
    finally:
        client_socket.close()
        print(f"Connection with {client_address} closed")


def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((HOST, PORT))
    server_socket.listen(5)  # Permite maximum 5 clienti
    print(f"Server running on {HOST}:{PORT}")

    while True:
        # Servul accepta un nou client
        client_socket, client_address = server_socket.accept()

        # Gestioneaza fiecare client pe un thread separat
        client_thread = threading.Thread(
            target=handle_client, args=(client_socket, client_address)
        )
        client_thread.start()


if __name__ == "__main__":
    main()
