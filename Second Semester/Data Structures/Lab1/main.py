from ui.read import readFile

if __name__ == '__main__':
    cars = readFile("fisier.in")
    print(cars[2].__str__())