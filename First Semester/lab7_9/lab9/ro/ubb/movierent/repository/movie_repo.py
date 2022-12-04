from ro.ubb.movierent.domain.movies_class import Movie

movie = Movie("1", "Once Upon A Time in Hollywood", "lorem10", "Action")
movie1 = Movie("2", "Avengers: Infinity Stone", "lorem25", "Superhero")

class MovieRepository:
    def __init__(self):
        self.__all_movies = []
        self.__all_movies.append(movie)
        self.__all_movies.append(movie1)

    def findAll(self):
        """
        Gaseste toate elementele de tip Movie si returneaza o lista cu acestea
        :return: o lista cu toate elementele
        """
        return list(self.__all_movies)

    def add(self, movie: Movie):
        """
        Adaugăm un element nou in lista
        :param movie: elementul nou
        :return:
        """
        try:
            if self.__findByTitle__(movie.get_title()) is None:#daca nu exista deja un film cu titlul din movie
                if self.__findById__(movie.get_id()) is None:#atunci verificam ca id-ul sa nu existe deja
                    self.__all_movies.append(movie)#si append-uim in lista de filme filmul dat ca parametru
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
            if self.__findById__(id) is not None:
                self.__all_movies.pop(int(id) - 1)#pop-uim "id-1" deoarece, natural, utilizatorul va oferi un id + 1
                #fata de indexarea normala a unei liste, astfel este necesar acest artificiu
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
            if self.__findById__(movie_id) is not None:#daca exista un element cu id-ul dat ca parametru
                self.__all_movies.insert(int(movie_id) - 1, new_movie)#adaugam in lista pe pozitia data ca parametru
                #elementul nou, modificat
            else:
                raise ValueError("Nu exista id-ul adaugat in lista")
        except ValueError as v:
            print(v)

    def __findById__(self, id):
        """
        Gaseste un element din lista dupa id
        :param id:
        :return: id-ul elementului daca exista, None in caz contrar
        """
        for i in range(0, len(self.__all_movies)):
            if self.__all_movies[i].get_id() == id:
                return self.__all_movies[i].get_id()
        return None

    def __findByTitle__(self, title):
        """
        Gaseste un element din lista dupa titlu (functie realizată pur cu scopul de utilizare in funtia de add)
        :param title: parametru dupa care sa caute elementele din lista
        :return: titlul elementului daca exista, None in caz contrar
        """
        for i in range(0, len(self.__all_movies)):
            if self.__all_movies[i].get_title() == title:
                return self.__all_movies[i].get_title()
        return None