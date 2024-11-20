from ro.ubb.movierent.repository.client_file_repo import ClientFileRepository
from ro.ubb.movierent.repository.client_repo import ClientRepository
from ro.ubb.movierent.repository.movie_file_repo import MovieFileRepository
from ro.ubb.movierent.repository.movie_repo import MovieRepository
from ro.ubb.movierent.service.movie_service import MovieService
from ro.ubb.movierent.service.client_service import ClientService
from ro.ubb.movierent.ui.console import Console
from ro.ubb.movierent.tests.movie_test import TestMovie

if __name__ == '__main__':
    movieRepo = MovieRepository()
    clientRepo = ClientRepository()
    movieService = MovieService(movieRepo)
    clientService = ClientService(clientRepo, movieRepo)
    consola = Console(movieService, clientService)
    consola.Menu()


