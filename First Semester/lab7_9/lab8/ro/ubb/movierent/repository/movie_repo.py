from ro.ubb.movierent.domain.movies_class import Movie

class MovieRepository:
    def __init__(self):
        self.__all_movies = []

    def add(self, movie: Movie):
        if self.__findByTitle__(movie.get_title()) is None:
            if self.__findById__(movie.get_id()) is None:
                self.__all_movies.insert(int(movie.get_id()), movie)
        else:
            raise ValueError("Exista deja un asemenea film")

    def detele(self, id):
        if self.__findById__(id) is not None:
            self.__all_movies.pop(int(id))
        else:
            raise ValueError("Nu exista id-ul adaugat in lista")

    def __findById__(self, id):
        for i in range(0, len(self.__all_movies)):
            if self.__all_movies[i].get_id() == id:
                return self.__all_movies[i].get_id()
        return None

    def __findByTitle__(self, title):
        for i in range(0, len(self.__all_movies)):
            if self.__all_movies[i].get_title() == title:
                return self.__all_movies[i].get_title()
        return None

    def __findByGenre__(self, genre):
        for i in range(0, len(self.__all_movies)):
            if self.__all_movies[i].get_genre() == genre:
                return self.__all_movies[i].get_genre()
        return None

    def findAll(self):
        return list(self.__all_movies)

    def updateId(self, movie, new_id):
        try:
            if self.__findById__(movie.get_id()) is not None:
                movie.set_id(new_id)
            else:
                raise ValueError("Nu exista id-ul adaugat in lista")
        except ValueError as v:
            print(v)

    def updateTitle(self, movie, new_title):
        try:
            if self.__findByTitle__(movie.get_title()) is not None:
                movie.set_title(new_title)
            else:
                raise ValueError("Nu exista un film cu titlul introdus")
        except ValueError as v:
            print(v)

    def updateGenre(self, movie, new_genre):
        try:
            if self.__findByGenre__(movie.get_genre()) is not None:
                movie.set_genre(new_genre)
            else:
                raise ValueError("Nu exista un film cu genul introdus")
        except ValueError as v:
            print(v)
