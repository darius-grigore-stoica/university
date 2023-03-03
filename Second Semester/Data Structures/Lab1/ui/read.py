from domain.car_class import Car

def readFile(filename):
    """
    Citirea din fisier a datelor
    :param filename:
    :return:
    """
    cars = []
    with open(filename, "r") as f:
        lines = f.readlines()
        for line in lines:
            car_brand = line.split()[0]
            car_model = line.split()[1]
            car_token = line.split()[2]
            car_priceBuy = line.split()[3]
            car_priceSell = line.split()[4]
            new_car = cars.append(Car(car_brand, car_model, car_token, car_priceBuy, car_priceSell))
            cars.append(new_car)
    return cars
