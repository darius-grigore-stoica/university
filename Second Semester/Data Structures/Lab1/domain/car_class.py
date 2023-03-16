from dataclasses import dataclass

@dataclass
class Car:
    def __init__(self, brand, model, token, priceBuy, priceSell):
        self.brand = brand
        self.model = model
        self.token = token
        self.priceBuy = priceBuy
        self.priceSell = priceSell
        self.profit = int(self.priceSell) - int(self.priceBuy)

    def __str__(self):
        return f"TOKEN: {self.token}\nBRAND: {self.brand}\nMODEL: {self.model}\nBUY PRICE: {self.priceBuy}\nSELL PRICE: {self.priceSell}\nPROFIT: {self.profit}"
