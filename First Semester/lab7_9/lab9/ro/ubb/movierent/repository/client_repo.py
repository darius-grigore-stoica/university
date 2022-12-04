from ro.ubb.movierent.domain.client_class import *

client = Client("1", "Andrei", "5030", [])

class ClientRepository:
    def __init__(self):
        self.__all_clients = []
        self.__all_clients.append(client)

    def findAll(self):
        """
        Gaseste toate elementele de tip Movie si returneaza o lista cu acestea
        :return: o lista cu toate elementele
        """
        return list(self.__all_clients)

    def add(self, client: Client):
        """
        Adaugăm un element nou in lista
        :param client: elementul nou
        :return:
        """
        try:
            if self.__findByName__(client.get_name()) is None:
                if self.__findById__(client.get_id()) is None:
                    self.__all_clients.append(client)
            else:
                raise ValueError("Exista deja un asemenea client")
        except ValueError as v:
            print(v)

    def delete(self, id):
        """
        Sterge un element din lista, dupa id
        :param id: id-ul elementului ce trebuie sters
        :return:
        """
        try:
            if self.__findById__(id) is not None:
                self.__all_clients.pop(int(id) - 1)#pop-uim "id-1" deoarece, natural, utilizatorul va oferi un id + 1
                #fata de indexarea normala a unei liste, astfel este necesar acest artificiu
            else:
                raise ValueError("Nu exista id-ul adaugat in lista")
        except ValueError as v:
            print(v)

    def update(self, client_id, new_client: Client):
        """
        Modifica un element specific din lista
        :param client_id: id-ul elementului ce trebuie modificat
        :param new_client: un obiect de tip Client, modificat
        :return:
        """
        try:
            if self.__findById__(client_id) is not None:
                self.__all_clients.insert(int(client_id) - 1, new_client)#adaugam in lista pe pozitia data ca parametru
                #elementul nou, modificat
            else:
                raise ValueError("Nu exista id-ul adaugat in lista")
        except ValueError as v:
            print(v)

    def __findById__(self, id):
        """
        Gaseste un element din lista dupa id
        :param id:
        :return: id-ul elementului daca exista, None in caz contrar
        """
        for i in range(0, len(self.__all_clients)):
            if self.__all_clients[i].get_id() == id:
                return self.__all_clients[i].get_id()
        return None

    def __findByName__(self, name):
        """
        Gaseste un element din lista dupa nume (functie realizată pur cu scopul de utilizare in funtia de add)
        :param name: parametru dupa care sa caute elementele din lista
        :return: numele elementului daca exista, None in caz contrar
        """
        for i in range(0, len(self.__all_clients)):
            if self.__all_clients[i].get_name() == name:
                return self.__all_clients[i].get_name()
        return None