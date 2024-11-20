# class Client:
#     def __init__(self, id, name, cnp, rented_movies):
#         self.__id = id
#         self.__name = name
#         self.__cnp = cnp
#         self.__rented_movies = rented_movies
#
#     #Definim GETTERI
#     def get_id(self):
#         return self.__id
#
#     def get_name(self):
#         return self.__name
#
#     def get_cnp(self):
#         return self.__cnp
#
#     def get_rented_movies(self):
#         return self.__rented_movies
#
#     #Definim SETTERI
#     def set_id(self, id):
#         self.__id = id
#
#     def set_name(self, name):
#         self.__name = name
#
#     def set_cnp(self, cnp):
#         self.__cnp = cnp
#
#     def set_rented(self, rented):
#         self.__rented_movies = rented
#
#     #Suprascriem functia str
#     def __str__(self):
#         print("Id: " + str(self.__id) + "\n" + "Nume: " + str(self.__name) + "\n" + "CNP: " + str(self.__cnp))
#         print("Lista de filme inchiriate:")
#         for index in range(0, len(self.__rented_movies)):
#             print(" * " + self.__rented_movies[index])
#         print("\n")
#
from dataclasses import dataclass

@dataclass
class Client:
    id: str
    name: str
    cnp: str
    rented_movies: list[str]

    def __str__(self):
        print("ID: " + self.id + "\n" +
              "Nume: " + self.name + "\n" +
              "CNP: " + self.cnp + "\n")
        print("Lista de filme inchiriate:")
        for index in range(0, len(self.rented_movies)):
                print(" * " + self.rented_movies[index])
                print("\n")