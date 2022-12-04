class Client:
    def __init__(self, id, name, cnp, rented_movies):
        self.__id = id
        self.__name = name
        self.__cnp = cnp
        self.__rented_movies = rented_movies

    #Definim GETTERI
    def get_id(self):
        return self.__id

    def get_name(self):
        return self.__name

    def get_cnp(self):
        return self.__cnp

    def get_rented_movies(self):
        return list(self.__rented_movies)

    #Definim SETTERI
    def set_id(self, id):
        self.__id = id

    def set_name(self, name):
        self.__name = name

    def set_cnp(self, cnp):
        self.__cnp = cnp

    def set_rented(self, rented):
        self.__rented_movies = rented

    #Suprascriem functia str
    def __str__(self):
        return "Client: \n" + str(self.__id) + "\n" + str(self.__name) + "\n" + str(self.__cnp) + "\n" + str(self.__rented_movies)

