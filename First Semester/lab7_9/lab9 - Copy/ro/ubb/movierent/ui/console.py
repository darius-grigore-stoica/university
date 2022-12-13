from ro.ubb.movierent.service.movie_service import MovieService
from ro.ubb.movierent.service.client_service import ClientService


class Console:
    def __init__(self, movieService: MovieService, clientService: ClientService):
        self.__movieService = movieService
        self.__clientService = clientService

    def addMovie(self):
        try:
            print("Id-ul filmului pe care doresti să il adaugi:  ")
            id = input("")
            print("Titlul filmul pe care doresti să il adaugi: ")
            title = input("")
            print("Descrierea filmul pe care doresti să il adaugi: ")
            description = input("")
            print("Genul filmul pe care doresti să il adaugi: ")
            genre = input("")
            self.__movieService.addMovie(id, title, description, genre, 0)
        except ValueError as v:
            print(v)

    def updateMovie(self):
        try:
            print("Id-ul filmul ce doresti a fi modificat: ")
            id = input("")
            print("Noul titlu: ")
            new_title = input("")
            print("Noua descriere: ")
            new_description = input("")
            print("Noul gen: ")
            new_genre = input("")
            print("Noul numar de inchirieri: ")
            rented_movies = input("")
            self.__movieService.updateMovie(id, new_title, new_description, new_genre, rented_movies)
        except ValueError as v:
            print(v)

    def deleteMovie(self):
        try:
            print("Id-ul filmului ce doresti a fi sters: ")
            id = input("")
            self.__movieService.deleteMovie(id)
        except ValueError as v:
            print(v)

    def find_allMovie(self):
        self.__movieService.find_allMovie()

    def addClient(self):
        try:
            print("Id-ul clientului pe care doresti să il adaugi: ")
            id = input("")
            print("Numele clientului pe care doresti să il adaugi: ")
            name = input("")
            print("Codul numeric personal al clientului pe care doresti să il adaugi: ")
            cnp = input("")
            rented_movies = []
            self.__clientService.addClient(id, name, cnp, rented_movies)
        except ValueError as v:
            print(v)

    def updateClient(self):
        try:
            print("Id-ul clientului ce doresti a fi modificat: ")
            id = input("")
            print("Noul id: ")
            new_id = input("")
            print("Noul nume: ")
            new_name = input("")
            print("Noul cod numeric personal: ")
            new_cnp = input("")
            new_rented_movies = []
            self.__clientService.updateClient(id, new_id, new_name, new_cnp, new_rented_movies)
        except ValueError as v:
            print(v)

    def deleteClient(self):
        try:
            print("Id-ul clientului ce doresti a fi sters: ")
            id = input("")
            self.__clientService.deleteClient(id)
        except ValueError as v:
            print(v)

    def rentMovie(self):
        try:
            print("Id-ul clientului ce doreste a inchiria un film: ")
            client_id = input("")
            print("Id-ul filmului inchiriat: ")
            movie_id = input("")
            self.__clientService.rentMovie(client_id, movie_id)
        except ValueError as v:
            print(v)

    def returnMovie(self):
        try:
            print("Id-ul clientului ce doreste a returna un film: ")
            client_id = input("")
            print("Id-ul filmului pentru returnare: ")
            movie_id = input("")
            self.__clientService.returnMovie(client_id, movie_id)
        except ValueError as v:
            print(v)

    def printMenu(self):
        print("1. Adauga film")
        print("2. Modifica film")
        print("3. Sterge film")
        print("4. Afiseaza toate filmele")
        print("5. Adauga client")
        print("6. Modifica client")
        print("7. Sterge client")
        print("8. Afiseaza toti clientii")
        print("9. Inchiriere film")
        print("10. Returnare film")
        print("x. Iesire")

    def Menu(self):
        while True:
            self.printMenu()
            optiune = input("Dati optiunea: ")
            if optiune == "1":
                self.addMovie()
            elif optiune == "2":
                self.updateMovie()
            elif optiune == "3":
                self.deleteMovie()
            elif optiune == "4":
                movies = self.__movieService.find_allMovie()
                for i in range(0, len(movies)):
                    movies[i].__str__()
            elif optiune == "5":
                self.addClient()
            elif optiune == "6":
                self.updateClient()
            elif optiune == "7":
                self.deleteClient()
            elif optiune == "8":
                clients = self.__clientService.find_allClient()
                for i in range(0, len(clients)):
                    clients[i].__str__()
            elif optiune == "9":
                self.rentMovie()
            elif optiune == "10":
                self.returnMovie()
            elif optiune == "x":
                break
            else:
                print("Optiune gresita, reincercati!")