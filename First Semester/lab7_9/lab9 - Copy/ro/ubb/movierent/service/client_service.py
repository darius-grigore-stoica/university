from ro.ubb.movierent.domain.client_class import Client
from ro.ubb.movierent.domain.movies_class import Movie
from ro.ubb.movierent.repository.client_repo import ClientRepository
from ro.ubb.movierent.repository.movie_repo import MovieRepository


class ClientService:
    def __init__(self, client_repository: ClientRepository, movie_repository: MovieRepository):
        self.__client_repository = client_repository
        self.__movie_repository = movie_repository

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

    def rentMovie(self, client_id, movie_id):
        client = Client(0, "", "", [])
        movie = Movie(0, "", "", "", [])
        try:
            clients = self.__client_repository.findAll()
            for index in range(0, len(clients)):
                if index == (int(client_id) - 1):
                    client_name = clients[index].get_name()
                    client_cnp = clients[index].get_cnp()
                    client_rented_movies = clients[index].get_rented_movies()
                    client = Client(client_id, client_name, client_cnp, client_rented_movies)
                    break
            movies = self.__movie_repository.findAll()
            for index in range(0, len(movies)):
                if index == (int(movie_id) - 1):
                    movie_title = movies[index].get_title()
                    movie_description = movies[index].get_description()
                    movie_genre = movies[index].get_genre()
                    movie_rentedTimes = movies[index].get_rentedTimes()
                    movie = Movie(movie_id, movie_title, movie_description, movie_genre, movie_rentedTimes)
                    break
            self.__client_repository.rentMovie(client, movie, self.__movie_repository)
        except ValueError as v:
            print(v)

    def returnMovie(self, client_id, movie_id):
        client = Client(0, "", "", [])
        movie = Movie(0, "", "", "", [])
        try:
            clients = self.__client_repository.findAll()
            for index in range(0, len(clients)):
                if index == (int(client_id) - 1):
                    client_name = clients[index].get_name()
                    client_cnp = clients[index].get_cnp()
                    client_rented_movies = clients[index].get_rented_movies()
                    client = Client(client_id, client_name, client_cnp, client_rented_movies)
                    break
            movies = self.__movie_repository.findAll()
            for index in range(0, len(movies)):
                if index == (int(movie_id) - 1):
                    movie_title = movies[index].get_title()
                    movie_description = movies[index].get_description()
                    movie_genre = movies[index].get_genre()
                    movie_rentedTimes = movies[index].get_rentedTimes()
                    movie = Movie(movie_id, movie_title, movie_description, movie_genre, movie_rentedTimes)
                    break
            self.__client_repository.returnMovie(client, movie, self.__movie_repository)
        except ValueError as v:
            print(v)