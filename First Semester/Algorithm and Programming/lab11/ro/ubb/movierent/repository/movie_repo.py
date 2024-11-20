from ro.ubb.movierent.domain.movies_class import Movie

class MovieRepository:
    def __init__(self):
        self._all_movies = []

    def findAll(self):
        """
        Gaseste toate elementele de tip Movie si returneaza o lista cu acestea
        :return: o lista cu toate elementele
        """
        return list(self._all_movies)

    def add(self, movie: Movie):
        """
        Adaugăm un element nou in lista
        :param movie: elementul nou
        :return:
        """
        try:
            if self.findByTitle(movie.get_title()) is None:#daca nu exista deja un film cu titlul din movie
                if self.findById(movie.get_id()) is None:#atunci verificam ca id-ul sa nu existe deja
                    self._all_movies.append(movie)#si append-uim in lista de filme filmul dat ca parametru
            else:
                raise ValueError("Exista deja un asemenea film")
        except ValueError as v:
            print(v)

    def delete(self, id):
        """
        Sterge un element din lista, dupa id
        :param id: id-ul elementului ce trebuie sters
        :return:
        """
        try:
            if self.findById(id) is not None:
                self._all_movies.pop(int(id) - 1)#pop-uim "id-1" deoarece, natural, utilizatorul va oferi un id + 1
                #fata de indexarea normala a unei liste, astfel este necesar acest artificiu
                for index in range(int(id) - 1, len(self._all_movies) + 1):
                    self._all_movies[index].set_id(int(self._all_movies[index].get_id()))
            else:
                raise ValueError("Nu exista id-ul adaugat in lista")
        except ValueError as v:
            print(v)

    def update(self, movie_id, new_movie: Movie):
        """
        Modifica un element specific din lista
        :param movie_id: id-ul elementului ce trebuie modificat
        :param new_movie: un obiect de tip Movie, modificat
        :return:
        """
        try:
            if self.findById(movie_id) is not None:#daca exista un element cu id-ul dat ca parametru
                    self._all_movies.pop(int(movie_id) - 1)
                    self._all_movies.insert(int(movie_id) - 1, new_movie)#adaugam in lista pe pozitia data ca parametru
                    #elementul nou, modificat

            else:
                raise ValueError("Nu exista id-ul adaugat in lista")
        except ValueError as v:
            print(v)

    def findById(self, id):
        """
        Gaseste un element din lista dupa id
        :param id:
        :return: id-ul elementului daca exista, None in caz contrar
        """
        for i in range(0, len(self._all_movies)):
            if self._all_movies[i].get_id() == id:
                return self._all_movies[i].get_id()
        return None

    def findByTitle(self, title):
        """
        Gaseste un element din lista dupa titlu (functie realizată pur cu scopul de utilizare in funtia de add)
        :param title: parametru dupa care sa caute elementele din lista
        :return: titlul elementului daca exista, None in caz contrar
        """
        for i in range(0, len(self._all_movies)):
            if self._all_movies[i].get_title() == title:
                return self._all_movies[i].get_title()
        return None

    def getMostWantedMovies(self):
        for index in range(0, len((self._all_movies))):
            for index2 in range(index, len(self._all_movies)):
                if int(self._all_movies[index].get_rentedTimes()) < int(self._all_movies[index2].get_rentedTimes()):
                    aux2 = self._all_movies[index]
                    self._all_movies[index] = self._all_movies[index2]
                    self._all_movies[index2] = aux2
        for index in range(0, len((self._all_movies))):
            self._all_movies[index].__str__()