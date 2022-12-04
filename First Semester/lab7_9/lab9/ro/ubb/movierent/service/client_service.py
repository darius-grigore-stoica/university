from ro.ubb.movierent.domain.client_class import Client
from ro.ubb.movierent.repository.client_repo import ClientRepository
from ro.ubb.movierent.repository.movie_repo import MovieRepository
from ro.ubb.movierent.service.movie_service import MovieService


class ClientService:
    def __init__(self, client_repository: ClientRepository):
        self.__client_repository = client_repository

    def find_allClient(self):
        return self.__client_repository.findAll()

    def addClient(self, id, name, cnp, rented_movies):
        client = Client(id, name, cnp, rented_movies)
        self.__client_repository.add(client)

    def updateClient(self, client_id, id, name, cnp, rented_movies):
        new_client = Client(id, name, cnp, rented_movies)
        self.__client_repository.update(client_id, new_client)

    def deleteClient(self, id):
        self.__client_repository.delete(id)

    def rentAMovie(self, client: Client, movie_title, all_movies):
        rented_movies = client.get_rented_movies()#preluam lista de filme inchiriare de client
        try:
            for index in range(0, len(all_movies)):
                if all_movies[index].get_title == movie_title:#daca in lista totala de filme, exista titlul filmului ce doreste clientul al inchiria
                    rented_movie.append(movie_title)#adaugam in lista clientului de filme inchiare titlul filmului primit ca parametru
                    break
                else:
                    raise ValueError("Acest film nu este disponibil")
        except ValueError as v:
            print(v)
        client.set_rented(rented_movies)#setam lista de filme inchiriare de client, lista creata mai sus
        self.__client_repository.update(client.get_id(), client)#apelam functia de update, pentru a modifica efectiv clientului nostru
