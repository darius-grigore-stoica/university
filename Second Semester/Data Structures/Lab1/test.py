from utils.sortari import bubbleSort
from ui.read import readFile

cars = readFile("fisier.in")
bubbleSort(cars, "token")
for i in range(0, len(cars)):
    print(cars[i].__str__() + "****\n")
