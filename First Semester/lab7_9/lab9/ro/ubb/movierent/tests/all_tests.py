from ro.ubb.movierent.tests.client_test import *
from ro.ubb.movierent.tests.movie_test import *

def TestAll():
    test_client_getteri()
    test_movie_getteri()
    test_client_setteri()
    test_movie_setteri()

    ### REPOSITORY ###
    test_add_movie()
    test_detele_movie()
    test_update_movie()
    test_add_client()
    test_delete_client()
    test_update_client()