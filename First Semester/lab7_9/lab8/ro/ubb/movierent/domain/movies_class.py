class Movie:
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
