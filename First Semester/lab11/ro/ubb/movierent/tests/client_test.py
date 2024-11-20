import unittest
from ro.ubb.movierent.domain.client_class import Client
from ro.ubb.movierent.repository.client_repo import ClientRepository
from ro.ubb.movierent.repository.movie_repo import MovieRepository
from ro.ubb.movierent.service.client_service import ClientService


class TestClient(unittest.TestCase):
    def setUp(self):
        self.client = Client("1", "Andrei Vlad", "5030291039029012", ["Once Upon A Time"])

    def test_id(self):
        self.assertTrue(self.client.get_id() == "1", "Id-ul trebuia să fie 1")
        self.client.set_id("2")
        self.assertTrue(self.client.get_id() == "2", "Id-ul trebuia să fie 2")

    def test_name(self):
        self.assertTrue(self.client.get_name() == "Andrei Vlad", "Numele trebuia să fie Andrei Vlad")
        self.client.set_name("Bogdan Mihai")
        self.assertTrue(self.client.get_name() == "Bogdan Mihai", "Numele trebuia să fie Bogdan Mihai")

    def test_cnp(self):
        self.assertTrue(self.client.get_cnp() == "5030291039029012", "CNP-ul trebuia sa fie 5030291039029012")
        self.client.set_cnp("5030123456729012")
        self.assertTrue(self.client.get_cnp() == "5030123456729012", "CNP-ul trebuia sa fie 5030123456729012")

    def test_rentedMovie(self):
        self.assertTrue(self.client.get_rented_movies() == ["Once Upon A Time"], "Lista trebuia sa fie Once Upon A Time")
        self.client.set_rented(["La la land"])
        self.assertTrue(self.client.get_rented_movies() == ["La la land"], "Lista trebuia sa fie La la land")

    def test_str(self):
        msg = self.client.__str__()
        self.assertTrue(self.client.__str__() == msg, "Nu s-a afisat corect!")

    def tearDown(self):
        pass

class TestClientRepository(unittest.TestCase):
    def setUp(self):
        self.clientRepo = ClientRepository()

    def test_findAll(self):
        self.assertTrue(len(self.clientRepo.findAll()) == 1, "Nu s-au gasit corect filmele din lista!")

    def test_add(self):
        client = Client("1", "Andrei Vlad", "5030291039029012", ["Once Upon A Time"])
        self.clientRepo.add(client)
        self.assertTrue(len(self.clientRepo.findAll()) == 1, "Nu s-a adaugat filmul in lista!")
        list = self.clientRepo.findAll()
        self.assertTrue(list[0].get_id() == "1", "Clientul nu a fost adaugat corect!")
        self.assertTrue(list[0].get_name() == "Andrei Vlad", "Clientul nu a fost adaugat corect!")
        self.assertTrue(list[0].get_cnp() == "5030291039029012", "Clientul nu a fost adaugat corect!")
        self.assertTrue(list[0].get_rented_movies() == ["Once Upon A Time"], "Clientul nu a fost adaugat corect!")

    def test_findByName(self):
        self.assertTrue(self.clientRepo.__findByName__("Andrei Vlad") == "Andrei Vlad", "S-a gasit clientului cu numele")
        self.assertTrue(self.clientRepo.__findByName__("Bognda") == None, "Nu s-a gasit clientului cu numele")

    def test_findById(self):
        self.assertTrue(self.clientRepo.__findById__("1") == "1", "S-a gasit clientului cu numele")
        self.assertTrue(self.clientRepo.__findById__("100") == None, "Nu s-a gasit clientului cu numele")

    def test_delete(self):
        client = Client("2", "Bogdan Vlad", "5030291039029012", ["Once Upon A Time"])
        self.clientRepo.add(client)
        self.clientRepo.delete("2")#stergem filmul din lista repo
        self.assertTrue(len(self.clientRepo.findAll()) == 1, "Nu s-a sters clientul din lista!")#si verificam ca lungimea sa ramana 1

    def test_update(self):
        client = Client("1", "Bogdan Vlad", "5030291039029012", ["Once Upon A Time"])
        self.clientRepo.update("1", client)
        list = self.clientRepo.findAll()
        self.assertTrue(list[0].get_id() == "1", "Clientul nu a fost modificat corect!")
        self.assertTrue(list[0].get_name() == "Bogdan Vlad", "Clientul nu a fost modificat corect!")
        self.assertTrue(list[0].get_cnp() == "5030291039029012", "Clientul nu a fost modificat corect!")
        self.assertTrue(list[0].get_rented_movies() == ["Once Upon A Time"], "Clientul nu a fost modificat corect!")

    def tearDown(self):
        pass


class TestClientService(unittest.TestCase):
    def setUp(self):
        self.clientRepo = ClientRepository()
        self.movieRepo = MovieRepository()
        self.clientService = ClientService(self.clientRepo, self.movieRepo)

    def test_findAll(self):
        id_1 = "1"
        name_1 = "Andrei"
        cnp_1 = "567890543215678"
        rentedMovie_1 = []
        self.clientService.addClient(id_1, name_1, cnp_1, rentedMovie_1)
        id_2 = "2"
        name_2 = "Alexandru"
        cnp_2 = "56789654324598"
        rentedMovie_2 = []
        self.clientService.addClient(id_2, name_2, cnp_2, rentedMovie_2)
        self.assertTrue(len(self.clientService.find_allClient()) == 2, "Nu s-au adaugat corect clientii in lista")

    def test_addClient(self):
        id = "1"
        name = "Andrei"
        cnp = "567890543215678"
        rentedMovie = ["La la land"]
        self.clientService.addClient(id, name, cnp, rentedMovie)
        self.assertTrue(len(self.clientService.find_allClient()) == 1, "Nu s-a adaugat clientul in lista!")
        list = self.clientService.find_allClient()
        self.assertTrue(list[0].get_id() == "1", "Clientul nu a fost adaugat corect!")
        self.assertTrue(list[0].get_name() == "Andrei", "Clientul nu a fost adaugat corect!")
        self.assertTrue(list[0].get_cnp() == "567890543215678", "Clientul nu a fost adaugat corect!")
        self.assertTrue(list[0].get_rented_movies() == ["La la land"], "Clientul nu a fost adaugat corect!")
        self.clientService.deleteClient("1")

    def test_deleteClient(self):
        id = "1"
        self.clientService.deleteClient(id)
        self.assertTrue(len(self.clientService.find_allClient()) == 1, "Nu s-a sters clientul din lista!")

    def test_updateClient(self):
        client_id = "1"
        name = "Andreea"
        cnp = "68954322345"
        rented_movies = []
        self.clientService.updateClient(client_id, client_id, name, cnp, rented_movies)
        list = self.clientService.find_allClient()
        self.assertTrue(list[1].get_id() == "1", "Clientul nu a fost modificat corect!")
        self.assertTrue(list[1].get_name() == "Andreea", "Clientul nu a fost modificat corect!")
        self.assertTrue(list[1].get_cnp() == "68954322345", "Clientul nu a fost modificat corect!")
        self.assertTrue(list[1].get_rented_movies() == [], "Clientul nu a fost modificat corect!")
