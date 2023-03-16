def findByToken(cars, atribut, x):
    """
    Returneaza informatii despre masina cu token-ul token dat ca parametru prin Cautare Secventiala
    :param cars: lista de masini
    :param atribut: atributul dupa care facem cautarea
    :param x: valoarea atributului dorit
    :return: obiectul de tip Car al carui atribut este x
    """
    for i in range (0, len(cars)):
        if getattr(cars[i], atribut) == x:
            return cars[i].__str__()
    return None

def findByTokenCB(cars, atribut, x):
    """
    Returneaza informatii despre masina cu token-ul token dat ca parametru prin Cautare Binara
    :param cars: lista de masini
    :param atribut: atributul dupa care facem cautarea
    :param x: valoarea atributului dorit
    :return: obiectul de tip Car al carui atribut este x
    """
    left = 0
    right = len(cars) - 1
    while left < right:
        mid = (left + right) // 2
        if getattr(cars[mid], atribut) == x:
            return cars[mid].__str__()
        elif getattr(cars[mid], atribut) > x:
            right = mid - 1
        else:
            left = mid + 1
    return None