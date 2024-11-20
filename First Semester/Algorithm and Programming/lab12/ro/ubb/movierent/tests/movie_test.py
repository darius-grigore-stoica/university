import unittest
from ro.ubb.movierent.domain.movies_class import Movie
from ro.ubb.movierent.repository.movie_repo import MovieRepository
from ro.ubb.movierent.service.movie_service import MovieService


class TestMovie(unittest.TestCase):
    def setUp(self):
        self.movie = Movie("1", "Once Upon A Time", "lorem25", "Action", "2")

    def test_id(self):
        self.assertTrue(self.movie.get_id() == "1", "Id-ul trebuia să fie 1")
        self.movie.set_id("2")
        self.assertTrue(self.movie.get_id() == "2", "Id-ul trebuia să fie 2")

    def test_title(self):
        self.assertTrue(self.movie.get_title() == "Once Upon A Time", "Titlul trebuia să fie Once Upon A Time")
        self.movie.set_title("La la land")
        self.assertTrue(self.movie.get_title() == "La la land", "Titlul trebuia să fie La la land")

    def test_description(self):
        self.assertTrue(self.movie.get_description() == "lorem25", "Descrierea trebuia sa fie lorem25")
        self.movie.set_description("lorem9")
        self.assertTrue(self.movie.get_description() == "lorem9", "Descrierea trebuia sa fie lorem9")

    def test_genre(self):
        self.assertTrue(self.movie.get_genre() == "Action", "Genul trebuia sa fie actiune")
        self.movie.set_genre("Drama")
        self.assertTrue(self.movie.get_genre() == "Drama", "Genul trebuia sa fie drama")

    def test_rentedTimes(self):
        self.assertTrue(self.movie.get_rentedTimes() == "2", "Numarul de inchirieri trebuia sa fie 2")
        self.movie.set_rentedTimes("1")
        self.assertTrue(self.movie.get_rentedTimes() == "1", "Numarul de inchirieri trebuia sa fie 1")

    def test_str(self):
        msg = self.movie.__str__()
        self.assertTrue(self.movie.__str__() == msg, "Nu s-a afisat corect filmul!")

    def tearDown(self) -> None:
        pass

class TestMovieRepository(unittest.TestCase):
    def setUp(self):
        self.movieRepo = MovieRepository()

    def test_findAll(self):
        movie = Movie("1")
        self.movieRepo.add(movie)
        self.assertTrue(len(self.movieRepo.findAll()) == 1, "Nu s-au gasit corect filmele din lista!")

    def test_add(self):
        movie = Movie("1", "Once Upon A Time", "lorem25", "Action", "2")
        self.movieRepo.add(movie)
#        self.assertTrue(len(self.movieRepo.findAll()) == 3, "Nu s-a adaugat filmul in lista!")
        list = self.movieRepo.findAll()
        self.assertTrue(list[3].get_id() == "4", "Filmul nu a fost adaugat corect!")
        self.assertTrue(list[3].get_title() == "Once Upon A Time", "Filmul nu a fost adaugat corect!")
        self.assertTrue(list[3].get_description() == "lorem25", "Filmul nu a fost adaugat corect!")
        self.assertTrue(list[3].get_genre() == "Action", "Filmul nu a fost adaugat corect!")
        self.assertTrue(list[3].get_rentedTimes() == "2", "Filmul nu a fost adaugat corect!")

    def test_delete(self):
        self.movieRepo.delete("2")#stergem filmul din lista repo
        #self.assertTrue(len(self.movieRepo.findAll()) == 3, "Nu s-a sters filmul din lista!")#si verificam ca lungimea sa ramana 1

    def test_update(self):
        movie = Movie("1", "La la land", "lorem9", "Drama", "1")
        self.movieRepo.update("1", movie)
        list = self.movieRepo.findAll()
        self.assertTrue(list[0].get_id() == "1", "Filmul nu a fost modificat corect!")
        self.assertTrue(list[0].get_title() == "La la land", "Filmul nu a fost modificat corect!")
        self.assertTrue(list[0].get_description() == "lorem9", "Filmul nu a fost modificat corect!")
        self.assertTrue(list[0].get_genre() == "Drama", "Filmul nu a fost modificat corect!")
        self.assertTrue(list[0].get_rentedTimes() == "1", "Filmul nu a fost modificat corect!")
        with self.assertRaises(ValueError):
            self.movieRepo.update("5",movie)

    def test_findById(self):
        self.assertTrue(self.movieRepo.findById("5") == None, "S-a gasit un id neexistent")
        self.assertTrue(self.movieRepo.findById("1") != None, "S-a gasit un id existent")

    def test_findByTitle(self):
        self.assertTrue(self.movieRepo.findByTitle("The Bastards") == None, "S-a gasit un titlu neexistent")
        self.assertTrue(self.movieRepo.findByTitle("La la land") != None, "S-a gasit un titlu existent")

    def test_getMostWantedMovies(self):
        movie = Movie("1", "La la land", "lorem9", "Drama", "10")
        movie1 = Movie("2", "Lorem", "lorem", "lorem", "2")
        movie2 = Movie("3", "Lorem1", "lorem1", "lorem1", "0")
        self.movieRepo.add(movie1)
        self.movieRepo.add(movie2)
        self.assertTrue(self.movieRepo.getMostWantedMovies() == movie.__str__(), "Nu s-a obtinut filmul dorit")


    def tearDown(self) -> None:
        pass

class TestMovieService(unittest.TestCase):
    def setUp(self):
        self.movieRepo = MovieRepository()
        self.movieService = MovieService(self.movieRepo)

    def test_findAll(self):
        id_1 = "1"
        title_1 = "The Bastards"
        description_1 = "lorem10"
        genre_1 = "Action"
        rentedTimes_1 = "2"
        self.movieService.addMovie(id_1, title_1, description_1, genre_1, rentedTimes_1)
        id_2 = "2"
        title_2 = "La la land"
        description_2 = "lorem20"
        genre_2 = "Drama"
        rentedTimes_2 = "1"
        self.movieService.addMovie(id_2, title_2, description_2, genre_2, rentedTimes_2)
        self.assertTrue(len(self.movieService.find_allMovie()) == 2, "Nu s-au adaugat corectele filmele in lista")

    def test_addMovie(self):
        id = "1"
        title = "Once Upon A Time"
        description= "lorem25"
        genre = "Action"
        rentedTimes = "2"
        self.movieService.addMovie(id, title, description, genre, rentedTimes)
        self.assertTrue(len(self.movieService.find_allMovie()) == 1, "Nu s-a adaugat filmul in lista!")
        list = self.movieService.find_allMovie()
        self.assertTrue(list[0].get_id() == "1", "Filmul nu a fost adaugat corect!")
        self.assertTrue(list[0].get_title() == "Once Upon A Time", "Filmul nu a fost adaugat corect!")
        self.assertTrue(list[0].get_description() == "lorem25", "Filmul nu a fost adaugat corect!")
        self.assertTrue(list[0].get_genre() == "Action", "Filmul nu a fost adaugat corect!")
        self.assertTrue(list[0].get_rentedTimes() == "2", "Filmul nu a fost adaugat corect!")

    def test_deleteMovie(self):
        id = "1"
        self.movieService.deleteMovie(id)
        self.assertTrue(len(self.movieService.find_allMovie()) == 1, "Nu s-a sters filmul din lista!")

    def test_updateMovie(self):
        movie_id = "1"
        title = "La la land"
        description = "lorem10"
        genre = "Drama"
        rentedTimes = "1"
        self.movieService.updateMovie(movie_id, title, description, genre, rentedTimes)
        list = self.movieService.find_allMovie()
        self.assertTrue(list[0].get_id() == "1", "Filmul nu a fost modificat corect!")
        self.assertTrue(list[0].get_title() == "La la land", "Filmul nu a fost modificat corect!")
        self.assertTrue(list[0].get_description() == "lorem10", "Filmul nu a fost modificat corect!")
        self.assertTrue(list[0].get_genre() == "Drama", "Filmul nu a fost modificat corect!")
        self.assertTrue(list[0].get_rentedTimes() == "1", "Filmul nu a fost modificat corect!")

    def tearDown(self) -> None:
        pass