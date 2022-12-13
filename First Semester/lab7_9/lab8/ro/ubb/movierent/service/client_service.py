from ro.ubb.movierent.repository.client_repo import ClientRepository
from ro.ubb.movierent.domain.client_class import Client


class ClientService:
    def __init__(self, client_repository: ClientRepository):
        self.__client_repository = client_repository

    def addMovie(self, id, name, cnp, rented_movies):
        client = Client(id, name, cnp, rented_movies)
        self.__client_repository.add(client)

    def deleteMovie(self, id):
        self.__client_repository.detele(id)
