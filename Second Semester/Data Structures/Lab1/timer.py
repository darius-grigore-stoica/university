import time
from ui.read import readFile
from utils.search import *
from utils.sortari import *

def timerFindByToken():
    cars = readFile("fisier.in")
    start = time.perf_counter()
    findByToken(cars, "token", "ke07khg1wn")
    end = time.perf_counter()
    print(f"Timp de executie Cautare Secventiala: {end - start:0.10f} second")

def timerFindByTokenCB():
    cars = readFile("fisier.in")
    quickSort(cars, 0, len(cars) - 1, "token")
    start = time.perf_counter()
    findByTokenCB(cars, "token", "ke07khg1wn")
    end = time.perf_counter()
    print(f"Timp de executie Cautare Binara: {end - start:0.10f} second")

def timerBubbleSort():
    cars = readFile("fisier.in")
    start = time.perf_counter()
    bubbleSort(cars, "token")
    end = time.perf_counter()
    print(f"Timp de executie Bubble Sort: {end - start:0.10f} second")

def timerQuickSort():
    cars = readFile("fisier.in")
    start = time.perf_counter()
    quickSort(cars, 0, len(cars) - 1, "token")
    end = time.perf_counter()
    print(f"Timp de executie Quick Sort: {end - start:0.10f} second")

if __name__ == '__main__':
    print("Cronometram cautarile: ")
    timerFindByToken()
    timerFindByTokenCB()
    print("\n|-------------------------|\n")
    print("Cronometram sortarile: ")
    timerBubbleSort()
    timerQuickSort()