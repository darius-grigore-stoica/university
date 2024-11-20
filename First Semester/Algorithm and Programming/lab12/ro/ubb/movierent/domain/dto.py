from dataclasses import dataclass

@dataclass
class Movie:
    id: str
    title: str
    description: str
    genre: str
    rented_times: str

    def __str__(self):
        print("Id: " + self.id + "\n" +
              "Titlu: " + self.title + "\n" +
              "Descriere: " + self.description + "\n" +
              "Gen: " + self.genre + "\n" +
              "Numarul de inchirieri: " + self.rented_times + "\n")