from ro.ubb.movierent.domain.movies_class import Movie
from ro.ubb.movierent.repository.movie_repo import MovieRepository

### TESTAM FUNCTIILE DIN CLASA MOVIE ###
def test_movie_getteri():
    movie = Movie("1", "Once Upon A Time in Hollywood", "lorem10", "Action")

    assert movie.get_id() == "1"
    assert movie.get_title() == "Once Upon A Time in Hollywood"
    assert movie.get_genre() == "Action"

def test_movie_setteri():
    movie = Movie("3", "Avengers: Infinity Stone", "lorem25", "Superhero")

    movie.set_id("1")
    assert movie.get_id() == "1"

    movie.set_title("Once Upon A Time in Hollywood")
    assert movie.get_title() == "Once Upon A Time in Hollywood"

    movie.set_description("50 years after the apocalyps some doctors are trying to find the reason why there's been such a spread of the Plague")
    assert movie.get_description() == "50 years after the apocalyps some doctors are trying to find the reason why there's been such a spread of the Plague"

### TESTAM FUNCTIILE DIN MOVIE_REPO ###
def test_add():
    all_movies = MovieRepository()
    movie = Movie("1", "Once Upon A Time in Hollywood", "lorem10", "Action")
    all_movies.add(movie)
    test_list = all_movies.findAll()
    assert test_list[0] == movie

def test_detele():
    all_movies = MovieRepository()
    movie = Movie("1", "Once Upon A Time in Hollywood", "lorem10", "Action")
    movie1 = Movie("3", "Avengers: Infinity Stone", "lorem25", "Superhero")
    all_movies.add(movie)
    all_movies.add(movie1)
    all_movies.detele(1)
    test_list = all_movies.findAll()
    assert test_list[0] == movie1
