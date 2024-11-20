from ro.ubb.movierent.domain.movies_class import Movie
from ro.ubb.movierent.repository.movie_repo import MovieRepository


class MovieFileRepository(MovieRepository):
    def __init__(self, file_name):
        super().__init__()
        self.__file_name = file_name
        self.__readFile()

    def add(self, movie):
        super().add(movie)
        self.__writeFile()

    def update(self, movie_id, new_movie):
        super().update(movie_id, new_movie)
        self.__writeFile()

    def delete(self, movie):
        super().delete(movie)
        self.__writeFile()

    def __readFile(self):
        with open(self.__file_name, 'r') as f:
            lines = f.readlines()
            for line in lines:
                linie = line.split(",")
                movie_id = linie[0]
                movie_title = linie[1]
                movie_description = linie[2]
                movie_genre = linie[3]
                movie_rentedTimes = linie[4].replace('\\n',"")
                movie = Movie(movie_id, movie_title, movie_description, movie_genre, movie_rentedTimes)
                self._all_movies.insert(int(movie_id) - 1, movie)

    def __writeFile(self):
        with open(self.__file_name, 'w') as f:
            for index in range(0, len(self._all_movies)):
                if self._all_movies[index].get_id() is not None:
                    f.write(f'{self._all_movies[index].get_id().__str__()},{self._all_movies[index].get_title().__str__()},{self._all_movies[index].get_description().__str__()},{self._all_movies[index].get_genre().__str__()},{self._all_movies[index].get_rentedTimes().__str__()} \n')