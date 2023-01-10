import unittest

from ro.ubb.movierent.domain.movies_class import Movie
from ro.ubb.movierent.repository.movie_repo import MovieRepository


class TestMovieMethods(unittest.TestCase):
    def add(self):
        #definim o lista de filme
        all_clients = MovieRepository()
        movie = Movie('1', 'La la land', 'lorem25', 'Drama', '1')
        all_clients.add(movie)
        self.assertEqual(all_clients.findAll(), ['1', 'La la land', 'lorem25', 'Drama', '1'])
