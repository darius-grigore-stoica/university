from ro.ubb.movierent.service.movie_service import *

class Console:
    def __init__(self, movieService: MovieService):
        self.__movieService = movieService

    def addMovie(self):
        try:
            id = input("Id-ul filmului ce doresti a fi adaugat: ")
            title = input("Titlul filmului ce doresti a fi adaugat: ")
            description = input("Descrierea filmului ce doresti a fi adaugat: ")
            genre = input("Genul filmului ce doresti a fi adaugat: ")
            self.__movieService.addMovie(id, title, description, genre)
        except ValueError as e:
            print(e)

    def Menu(self):
        menu = "1. Adaugă film în listă\n"
        menu += "2. Sterge film bazat pe id\n"
        menu += "3. Modifică id-ul unui film\n"
        menu += "4. Modifică titlul unui film\n"
        menu += "5. Modifică descrierea unui film\n"
        menu += "p. Afisarea tuturor filmelor\n"
        menu += "x. Ieșire din program\n"
        print(menu)

    def Program(self):
        while True:
            self.Menu()
            comanda = input("Introduceti comanda: ")
            if comanda == "1":
                self.addMovie()
            elif comanda == "x" or comanda == "X":
                break
            else:
                print("Optiune gresita, reincercati!")
