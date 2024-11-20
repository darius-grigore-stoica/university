from ro.ubb.movierent.domain.client_class import Client
from ro.ubb.movierent.domain.movies_class import Movie
from ro.ubb.movierent.repository.client_repo import ClientRepository
from ro.ubb.movierent.repository.movie_repo import MovieRepository
from ro.ubb.movierent.service.client_service import *


### TESTAM FUNCTIILE DIN CLASA CLIENT ###
def test_client_getteri():
    client = Client("1", "Popescu Mihai", "0123456789", ["Once Upon A Time in Hollywood", "Avengers: Infinity Stone"])

    assert client.get_id() == "1"
    assert client.get_name() == "Popescu Mihai"
    assert client.get_cnp() == "0123456789"
    assert client.get_rented_movies() == ["Once Upon A Time in Hollywood", "Avengers: Infinity Stone"]


def test_client_setteri():
    client = Client("1", "Popescu Mihai", "0123456789", ["Once Upon A Time in Hollywood", "Avengers: Infinity Stone"])

    client.set_id("5")
    assert client.get_id() == "5"

    client.set_name("Bogdan Ispas")
    assert client.get_name() == "Bogdan Ispas"

    client.set_cnp("0250402123")
    assert client.get_cnp() == "0250402123"

    client.set_rented(["Avenger: Endgame"])
    assert client.get_rented_movies() == ["Avenger: Endgame"]

### TESTAM FUNCTIILE DIN CLIENT_REPO ###

def test_add_client():
    all_clients = ClientRepository()
    client = Client("1", "Popescu Mihai", "0123456789", ["Once Upon A Time in Hollywood", "Avengers: Infinity Stone"])
    all_clients.add(client)
    test_list = all_clients.findAll()
    assert test_list[0] == client

def test_delete_client():
    all_clients = ClientRepository()
    client = Client("1", "Popescu Mihai", "0123456789", ["Once Upon A Time in Hollywood", "Avengers: Infinity Stone"])
    client1 = Client("2", "Bogdan Stefan", "9876543217", ["La La Land (2018)"])
    all_clients.add(client)
    all_clients.add(client1)
    all_clients.delete("1")
    test_list = all_clients.findAll()
    assert test_list[0] == client1

def test_update_client():
    all_clients = ClientRepository()
    client = Client("1", "Popescu Mihai", "0123456789", ["Once Upon A Time in Hollywood", "Avengers: Infinity Stone"])
    client1 = Client("2", "Bogdan Stefan", "9876543217", ["La La Land (2018)"])
    all_clients.add(client)
    all_clients.add(client1)
    all_clients.update("1", client1)
    test_list = all_clients.findAll()
    assert test_list[0] == client1
