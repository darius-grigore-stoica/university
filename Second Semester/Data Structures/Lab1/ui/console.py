from ui.read import readFile
from utils.sortari import *
from utils.search import *
from domain.car_class import Car

class Console:
    def __init__(self):
        self.cars = []

    def printMenu(self):
        self.menu = ""
        self.menu += "1. Citeste lista de masini\n"
        self.menu += "2. Sortea lista de masini dupa un anumit criteriu\n"
        self.menu += "3. Cauta in lista de masini un element dupa valoarea unui anumit atribut\n"
        self.menu += "4. Afiseaza lista de masini\n"
        self.menu += "0. Iesire din aplicatie"
        return self.menu

    def readCarList(self):
        self.cars = readFile("fisier.in")

    def sortCarList(self, criteriu):
        quickSort(self.cars, 1, len(self.cars) - 1, criteriu)

    def findElemInList(self, atribut, valoare):
        print(findByToken(self.cars, atribut, valoare))

    def printCarList(self):
        for i in range(0, len(self.cars)):
            print(self.cars[i])
            print("\n")

    def Menu(self):
        ruleaza = True
        while(ruleaza):
            print(self.printMenu())
            option = input("")
            if option == "1":
                self.readCarList()
            elif option == "2":
                print("Care este criteriu dupa care doriti sortarea?")
                criteriu = input("")
                self.sortCarList(criteriu)
            elif option == "3":
                print("Care este atributului dupa care doriti cautarea?")
                atribut = input("")
                print("Care este valoarea atributului elementelui cautat?")
                valoare = input("")
                self.findElemInList(atribut, valoare)
            elif option == "4":
                self.printCarList()
            elif option == "0":
                ruleaza = False
            else:
                print("Optione Gresita! Incercati din nou!")