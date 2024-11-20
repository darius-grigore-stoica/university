from ro.ubb.movierent.tests.movie_test import *
from ro.ubb.movierent.tests.client_test import *

movie = TestMovie()
movie_repo = TestMovieRepository()
movie_service = TestMovieService()

client = TestClient()
client_repo = TestClientRepository()
client_service = TestClientService()

def test_movie():
    movie.setUp()
    movie.test_id()
    movie.test_title()
    movie.test_description()
    movie.test_genre()
    movie.test_rentedTimes()
    movie.test_str()
    movie.tearDown()

    movie_repo.setUp()
    movie_repo.test_findAll()
    movie_repo.test_add()
    movie_repo.test_delete()
    movie_repo.test_update()
    movie_repo.test_findById()
    movie_repo.test_findByTitle()
    movie_repo.test_getMostWantedMovies()
    movie_repo.tearDown()

    movie_service.setUp()
    movie_service.test_addMovie()
    movie_service.test_findAll()
    movie_service.test_updateMovie()
    movie_service.test_deleteMovie()
    movie_service.tearDown()


def test_client():
    client.setUp()
    client.test_id()
    client.test_cnp()
    client.test_name()
    client.test_rentedMovie()
    client.test_str()
    client.tearDown()

    client_repo.setUp()
    client_repo.test_add()
    client_repo.test_findByName()
    client_repo.test_findByName()
    client_repo.test_findAll()
    client_repo.test_delete()
    client_repo.test_update()
    client_repo.tearDown()

    client_service.setUp()
    client_service.test_addClient()
    client_service.test_findAll()
    client_service.test_updateClient()
    client_service.test_deleteClient()
    client_service.tearDown()

def allTest():
    test_movie()
    test_client()