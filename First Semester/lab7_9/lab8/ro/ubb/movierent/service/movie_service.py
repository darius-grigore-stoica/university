from ro.ubb.movierent.repository.movie_repo import MovieRepository

class MovieService:
    def __init__(self, movie_repository: MovieRepository):
        self.__movie_repository = movie_repository

    def addMovie(self, id, title, description, genre, rented_times):
        movie = Movie(id, title, description, genre, rented_times)
        self.__movie_repository.add(movie)

    def deleteMovie(self, id):
        self.__movie_repository.detele(id)
