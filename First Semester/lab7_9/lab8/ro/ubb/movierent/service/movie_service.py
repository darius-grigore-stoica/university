from ro.ubb.movierent.repository.movie_repo import MovieRepository

class MovieService:
    def __init__(self, movie_repository: MovieRepository):
        self.__movie_repository = movie_repository
