from ro.ubb.movierent.domain.client_class import Client


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