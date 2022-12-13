from ro.ubb.movierent.repository.client_repo import ClientRepository
from ro.ubb.movierent.repository.movie_file_repo import MovieFileRepository
from ro.ubb.movierent.repository.movie_repo import MovieRepository
from ro.ubb.movierent.service.movie_service import MovieService
from ro.ubb.movierent.service.client_service import ClientService
from ro.ubb.movierent.ui.console import Console
from tests.all_tests import TestAll

if __name__ == '__main__':
    #TestAll()

    movieRepo = MovieFileRepository("movies.txt")
    clientRepo = ClientRepository()
    movieService = MovieService(movieRepo)
    clientService = ClientService(clientRepo, movieRepo)

    consola = Console(movieService, clientService)
    consola.Menu()


