from utils.sortari import *
from utils.search import *
from ui.console import *
from ui.read import readFile

def Testing():
    testcars = Console()
    testcars.readCarList()
    assert findByToken(testcars.cars,"brand","Chevrolet") is not None
    assert findByToken(testcars.cars,"brand","Opel") is None
    assert findByTokenCB(testcars.cars, "brand", "Chevrolet") is not None
    assert findByTokenCB(testcars.cars, "brand", "Opel") is None
    bubbleSort(testcars.cars,"brand")
    assert testcars.cars[0].brand == "Audi"
    quickSort(testcars.cars,1,len(testcars.cars)-1,"token")
    assert testcars.cars[0].token == "0ibdu3n47t"