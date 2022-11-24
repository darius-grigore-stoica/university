from ro.ubb.movierent.domain.client_class import Client

class ClientRepository:
    def __init__(self):
        self.__all_clients = []

    def add(self, id, name, cnp, rented_movies):
        if self.__findByName__(name) is None:
            if self.__findById__(id) is None:
                client = Client(id, name, cnp, rented_movies)
                self.__all_clients.insert(id, client)
        else:
            raise ValueError("Exista deja un asemenea client")

    def detele(self, id):
        if self.__findById__(id) is not None:
            del self.__all_clients[id]
        else:
            raise ValueError("Nu exista id-ul adaugat in lista")

    def findAll(self):
        return self.__all_clients

    def __findById__(self, id):
        for i in range(0, len(self.__all_clients)):
            if self.__all_clients[i].get_id() == id:
                return self.__all_clients[i].get_id()
        return None

    def __findByName__(self, name):
        for i in range(0, len(self.__all_clients)):
            if self.__all_clients[i].get_name() == name:
                return self.__all_clients[i].get_name()
        return None

    def __findByCnp__(self, cnp):
        for i in range(0, len(self.__all_clients)):
            if self.__all_clients[i].get_cnp() == cnp:
                return self.__all_clients[i].get_cnp()
        return None

    def updateId(self, client, new_id):
        if self.__findById__(client.get_id()) is not None:
            client.set_id(new_id)
        else:
            raise ValueError("Nu exista id-ul adaugat in lista")

    def updateName(self, client, new_name):
        if self.__findByName__(client.get_title()) is not None:
            client.set_name(new_name)
        else:
            raise ValueError("Nu exista un client cu numele introdus")

    def updateCnp(self, client, new_cnp):
        if self.__findByCnp__(client.get_cnp()) is not None:
            client.set_cnp(new_cnp)
        else:
            raise ValueError("Nu exista un client cu cnp-ul introdus")
