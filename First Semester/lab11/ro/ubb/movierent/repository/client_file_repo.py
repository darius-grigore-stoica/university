from ro.ubb.movierent.domain.client_class import Client
from ro.ubb.movierent.repository.client_repo import ClientRepository


class ClientFileRepository(ClientRepository):
    def __init__(self, file_name):
        super().__init__()
        self.__file_name = file_name
        self.__readFile()

    def add(self, client):
        super().add(client)
        self.__writeFile()

    def update(self, client_id, new_client):
        super().update(client_id, new_client)
        self.__writeFile()

    def delete(self, client):
        super().delete(client)
        self.__writeFile()

    def __readFile(self):
        with open(self.__file_name, 'r') as f:
            lines = f.readlines()
            for line in lines:
                client_id = line.split(",")[0]
                client_name = line.split(",")[1]
                client_cnp = line.split(",")[2]
                client_rented_movies = line.split(",")[3]
                client = Client(client_id, client_name, client_cnp, client_rented_movies)
                self._all_clients.insert(int(client_id) - 1, client)

    def __writeFile(self):
        with open(self.__file_name, 'w') as f:
            for index in range(0, len(self._all_clients)):
                if self._all_clients[index].get_id() is not None:
                    f.write(f'{self._all_clients[index].get_id().__str__()},{self._all_clients[index].get_name().__str__()},{self._all_clients[index].get_cnp().__str__()},{self._all_clients[index].get_rented_movies().__str__()}')
