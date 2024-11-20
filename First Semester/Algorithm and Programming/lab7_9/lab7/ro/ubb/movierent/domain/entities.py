
#Aici vom definii clasele de care avem nevoie pentru realizarea proiectului


class Movies:
    def __init__(self, id, title, description, genre):
        self.__id = id
        self.__title = title
        self.__description = description
        self.__genre = genre

    #Definim GETTERI
    def get_id(self):
        return self.__id

    def get_title(self):
        return self.__title

    def get_description(self):
        return self.__description

    def get_genre(self):
        return self.__genre

    #Definim SETTERI
    def set_id(self, id):
        self.__id = id

    def set_title(self, title):
        self.__title = title

    def set_description(self, description):
        self.__description = description

    def set_genre(self, genre):
        self.__genre = genre

    #Suprascriem functia str
    def __str__(self):
        return "Film: \n" + str(self.__id) + "\n" + str(self.__title) + "\n" + str(self.__description) + "\n" + str(self.__genre) + "\n"

class Clients:
    def __init__(self, id, name, cnp):
        self.__id = id
        self.__name = name
        self.__cnp = cnp

    #Definim GETTERI
    def get_id(self):
        return self.__id

    def get_name(self):
        return self.__name

    def get_cnp(self):
        return self.__cnp

    #Definim SETTERI
    def set_id(self, id):
        self.__id = id

    def set_name(self, name):
        self.__name = name

    def set_description(self, cnp):
        self.__cnp = cnp

    #Suprascriem functia str
    def __str__(self):
        return "Film " + str(self.__id) + " " + str(self.__name) + "\n" + str(self.__cnp) + "\n"

