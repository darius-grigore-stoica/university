from ro.ubb.movierent.domain.movies_class import Movie
from ro.ubb.movierent.repository.movie_repo import MovieRepository


class MovieService:
    def __init__(self, movie_repository: MovieRepository):
        self.__movie_repository = movie_repository

    def find_allMovie(self):
        return self.__movie_repository.findAll()

    def addMovie(self, id, title, description, genre):
        """
        Adaugarea unui element nou
        :param id:
        :param title:
        :param description:
        :param genre:
        :return:
        """
        movie = Movie(id, title, description, genre)
        self.__movie_repository.add(movie)

    def updateMovie(self, movie_id, id, title, description, genre):
        """
        Modificare unui element
        :param movie_id:
        :param id:
        :param title:
        :param description:
        :param genre:
        :return:
        """
        new_movie = Movie(id, title, description, genre)
        self.__movie_repository.update(movie_id, new_movie)

    def deleteMovie(self, id):
        """
        Stergerea unui element
        :param id:
        :return:
        """
        self.__movie_repository.delete(id)
