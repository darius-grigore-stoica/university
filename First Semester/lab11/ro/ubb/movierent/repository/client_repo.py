import math

from ro.ubb.movierent.domain.client_class import *
from ro.ubb.movierent.domain.movies_class import Movie
from ro.ubb.movierent.repository.movie_repo import MovieRepository


class ClientRepository:
    def __init__(self):
        self._all_clients = []

    def add(self, client: Client):
        """
        Adaugăm un element nou in lista
        :param client: elementul nou
        :return:
        """
        try:
            if self.__findByName__(client.get_name()) is None:
                if self.__findById__(client.get_id()) is None:
                    self._all_clients.append(client)
                else:
                    raise ValueError("Exista deja un client cu acest id")
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
                self._all_clients.pop(int(id) - 1)#pop-uim "id-1" deoarece, natural, utilizatorul va oferi un id + 1
                #fata de indexarea normala a unei liste, astfel este necesar acest artificiu
            else:
                raise ValueError("Nu exista id-ul adaugat in lista")
        except ValueError as v:
            print(v)

    def update(self, client_id, new_client: Client):
        """
        Modifica un element specific din lista
        :param client_id: id-ul elementului ce trebuie modificat
        :param new_client: un obiect de tip Client, modificat
        :return:
        """
        try:
            if self.__findById__(client_id) is not None:
                self._all_clients.pop(int(client_id) - 1)
                self._all_clients.insert(int(client_id), new_client)#adaugam in lista pe pozitia data ca parametru
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
        for i in range(0, len(self._all_clients)):
            if self._all_clients[i].get_id() == id:
                return self._all_clients[i].get_id()
        return None

    def __findByName__(self, name):
        """
        Gaseste un element din lista dupa nume (functie realizată pur cu scopul de utilizare in funtia de add)
        :param name: parametru dupa care sa caute elementele din lista
        :return: numele elementului daca exista, None in caz contrar
        """
        for i in range(0, len(self._all_clients)):
            if self._all_clients[i].get_name() == name:
                return self._all_clients[i].get_name()
        return None

    def findAll(self):
        """
        Gaseste toate elementele de tip Movie si returneaza o lista cu acestea
        :return: o lista cu toate elementele
        """
        return list(self._all_clients)

    def rentMovie(self, client, movie, all_movies: MovieRepository):
        try:
            if all_movies.findById(movie.get_id()) is not None:
                # select client's rented list
                rented_list = client.get_rented_movies()
                #modify client's movies rented list
                rented_list.append(movie.get_title())
                #increase the number of times that movie what rented
                new_movie = Movie(movie.get_id(), movie.get_title(), movie.get_description(), movie.get_genre(), int(movie.get_rentedTimes()) + 1)
                all_movies.update(movie.get_id(), new_movie)
                client.set_rented(rented_list)
                #add the new-formed client to the client list
                client_id = client.get_id
                if self.__findById__(client_id) is not None:
                    self._all_clients.pop(int(client_id) - 1)
                    self._all_clients.insert(int(client_id), client)  # adaugam in lista pe pozitia data ca parametru
                    # elementul nou, modificat
            else:
                raise ValueError("Nu exista filmul")
        except ValueError as v:
            print(v)

    def returnMovie(self, client, movie, all_movies: MovieRepository):
        try:
            if all_movies.findById(client.get_id()) is not None:
                # select client's rented list
                rented_list = client.get_rented_movies()
                #modify client's movies rented list
                for index in range(0, len(rented_list)):
                    if rented_list[index] == movie.get_title():
                        rented_list.pop(index)
                #decrease the number of times that movie what rented
                if (int(movie.get_rentedTimes())) > 0:
                    new_movie = Movie(movie.get_id(), movie.get_title(), movie.get_description(), movie.get_genre(), int(movie.get_rentedTimes()) - 1)
                else:
                    new_movie = Movie(movie.get_id(), movie.get_title(), movie.get_description(), movie.get_genre(), 0)
                all_movies.update(movie.get_id(), new_movie)
                client.set_rented(rented_list)
                #add the new-formed client to the client list
                client_id = client.get_id
                if self.__findById__(client_id) is not None:
                    self._all_clients.pop(int(client_id) - 1)
                    self._all_clients.insert(int(client_id), client)  # adaugam in lista pe pozitia data ca parametru
                    # elementul nou, modificat
            else:
                raise ValueError("Nu exista filmul")
        except ValueError as v:
            print(v)

    def sortClientsByNumberOfMovies(self):
        # get the lenghts of rented_movie lists of every client
        numberOfMovies = [0] * len(self._all_clients)
        for index in range(0, len(self._all_clients)):
            numberOfMovies[index] = len(self._all_clients[index].get_rented_movies())
        #sort the clients base by number of movies
        for index in range(0, len((self._all_clients))):
            for index2 in range(index, len(self._all_clients)):
                if numberOfMovies[index] < numberOfMovies[index2]:
                    aux = numberOfMovies[index]
                    numberOfMovies[index] = numberOfMovies[index2]
                    numberOfMovies[index2] = aux
                    aux2 = self._all_clients[index]
                    self._all_clients[index] = self._all_clients[index2]
                    self._all_clients[index2] = aux2
        for index in range(0, len((self._all_clients))):
                self._all_clients[index].__str__()

    def primary30Percentage(self):
        print("Lista ordonată a clientilor după numărul de filme: ")
        self.sortClientsByNumberOfMovies()
        print("Primi 30% din aceasta lista sunt: ")
        for index in range(0, math.ceil((30 * len(self._all_clients)) / 100)):
            self._all_clients[index].__str__()