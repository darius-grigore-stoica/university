def findByToken(cars, token):
    """
    Returneaza informatii despre masina cu token-ul token dat ca parametru
    :param cars: lista de masini
    :param token: token-ul cautat
    :return:
    """
    for i in cars:
        if i.token == token:
            return i.__str__()
    return None