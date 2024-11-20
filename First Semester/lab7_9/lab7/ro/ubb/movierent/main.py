from domain.entities import *
from repository.rentmovie_repository import *

if __name__ == '__main__':
    all_movies = []
    movie1 = Movies(1, "Once Upon a Time", "Lorem10", "Drama")
    movie2 = Movies(2, "Bastards", "Lorem5", "Action")
    all_movies[0].__add(movie1)
    all_movies.append(movie2)
    for id in range(0, len(all_movies)):
        print(all_movies[id].__str__())