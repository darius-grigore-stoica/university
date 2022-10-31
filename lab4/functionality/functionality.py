"""
Temă: laborator 4 - 6
Stoica Grigore Darius
Grupa: 316/1 - 22.10.2022
"""

"""
Cerință: gestiunea concurenților de la un concurs de programare.
Programul înregistrează scorul obținut de fiecare concurent la 10 probe diferite,
fiecare probă este notat cu un scor de la 1 la 10. Fiecare participant este
identificat printr-un număr de concurs, scorul este ținut într-o listă unde
concurentul 3 are scorul pe poziția 3 în listă .
"""

##
##----------------------FUNCTIONALITATI
##


#1. Adaugă un scor la un participant.
def AddNewParticipant():
    """
    Adaugă scor pentru un nou participant (ultimul participant)
    :return: final_list cu un nou concurent adaugat
    """
    participant = []
    for i in range(0, 10):
        elem = int(input(""))
        participant.append(elem)
    return participant

def InsertScoreForGivenParticipant(final_list, participant_index, score_index, inserted_score):
    """
    Inserare scor pentru un participant.
    :param final_list: listă a scorurilor obtinute de fiecare concurent
    :param participant_index: numarul de ordine al participantului căruia îi insera scor
    :param score_index: pozitia probei a carui scor vrem să il modificam
    :param inserted_score: scorul nou inserat in lista finala
    :return: final_list cu modificările de mai sus
    """
    if participant_index < len(final_list) and score_index < len(final_list[participant_index]):
        final_list[participant_index][score_index] = inserted_score
    elif participant_index >= len(final_list):
        final_list.append([0])
        for i in range(0, score_index):
            final_list[participant_index].append(0)
        final_list[participant_index][score_index] = inserted_score
    elif (score_index >= len(final_list[participant_index]) and participant_index < len(final_list)):
        for i in range(0, score_index):
            final_list[participant_index].append(0)
        final_list[participant_index][score_index] = inserted_score
    return final_list


#2. Modificare scor.
def EraseScoreForGivenParticipant(final_list, participant_index, score_index):
    """
    Șterge scorul pentru un participant dat.
    :param final_list: listă a scorurilor obtinute de fiecare concurent
    :param participant_index: numarul de ordine al participantului căruia îi stergem scorului
    :param score_index: pozitia scorului ce se doreste a fi sters din lista
    :return: final_list cu modificările de mai sus
    """
    if participant_index < len(final_list):
        if score_index < len(final_list[participant_index]):
            final_list[participant_index][score_index] = 0
    return final_list

def EraseScoreForInterval(final_list, participant_start, participant_end):
    """
    Șterge scorul pentru un interval de participanți.
    :param final_list: listă a scorurilor obtinute de fiecare concurent
    :param participant_start: indicele de inceputul al intervalului de participanti pentru care stergem scorul
    :param participant_end: indicele de start al intervalului
    :return: final_list cu modificările din enunt
    """
    if(participant_start < participant_end and participant_start > 0 and participant_end < len(final_list)):
        del final_list[participant_start:participant_end + 1]
    return final_list

def SubstituteScore(final_list, participant_index, score_index, substitute_score):
    """
    Înlocuiește scorul de la un participant.
    :param final_list: listă a scorurilor obtinute de fiecare concurent
    :param participant_index: numarul de ordine al participantului căruia îi inlocuim scorul
    :param score_index: pozitia scorului ce se doreste a fi inlocuit
    :param substitute_score: valoarea scorului dupa inlocuire
    :return: final_list cu modificările din enunt
    """
    if participant_index < len(final_list):
        if score_index < len(final_list[participant_index]):
            final_list[participant_index][score_index] = substitute_score
    return final_list


#3. Tipărește lista de participanți.
def getAverageScore(final_list, participant_index):
    """
    Calculează media scorurilor obtinute de un participant dat
    :param final_list: listă a scorurilor obtinute de fiecare concurent
    :param participant_index:  numarul de ordine al participantului căruia îi calculăm media
    :return: media aritmetică a scorurilor obtinute de participant
    """
    sum = 0
    for i in range(0, len(final_list[participant_index])):
        sum += final_list[participant_index][i]
    if len(final_list[participant_index]) > 0:
        return int(sum / len(final_list[participant_index]))
    return 0

def getListOfScores(final_list):
    """
    Obtine o listă a mediilor aritmetice a scorurilor obtinute de concurenti
    :param final_list:
    :return: o listă ce contine media obtinute de fiecare participant, pe pozitia echivalenta cu numarul de ordine al acestuia
    """
    score = []
    for i in range(0, len(final_list)):
        score.append(getAverageScore(final_list, i))
    return score

def WriteLowerScoreParticipant(final_list, given_score):
    """
    Tipărește participanții care au scor mai mic decât un scor dat.
    :param final_list: listă a scorurilor obtinute de fiecare concurent
    :param given_score: scorul dat din enunt
    :return: tipareste pe ecran numarul de ordine a participantilor ce respecta conditia din enunt
    """
    if (given_score > 0 and given_score <= 10):
        return 0
    scores = getListOfScores(final_list)
    for i in range(0, len(scores)):
        if scores[i] < given_score:
            print(i + 1)

def WriteSortedParticipant(score):
    """
    Tipărește participanții ordonat după scor
    :param score: listă ce contine media obtinute de fiecare participant
    :return: tipareste pe ecran numarul de ordine a participantilor ordonati crescator dupa scor
    """
    writen = 0
    while writen < len(score):
        max, max_index = 0, -1
        for i in range (0, len(score)):
            if max < score[i]:
                max = score[i]
                max_index = i
        if(max_index > 0):
            print(max_index)
        score[max_index] = 0
        writen += 1

def WriteGivenScoreSorted(final_list, given_score):
    """
    Tipărește participanții cu scor mai mare decât un scor dat, ordonat după scor.
    :param final_list: lista ce contine scorurile obtinute de fiecare concurent
    :param given_score: scorul dat din enunt
    :return: tipareste pe ecran numarul de ordine a participantilor ordonati crescator dupa scor, care respecta conditia din enunt
    """
    if (given_score > 0 and given_score <= 10):
        return 0
    score = getListOfScores(final_list)
    for i in range(0, len(score)):
        if score[i] < given_score:
            score[i] = 0
    WriteSortedParticipant(score)


#4. Operații pe un subset de participanți.
def WriteAvaregeScoreForInterval(final_list, participant_start, participant_end):
    """
    Calculează media scorurilor pentru un interval dat
    :param final_list:
    :param participant_start:
    :param participant_end:
    :return:
    """
    if participant_end < participant_start:
        return 0
    scores = getListOfScores(final_list)
    for i in range(participant_start, participant_end + 1):
        print(scores[i])

def getMinimScoreForInterval(final_list, participant_start, participant_end):
    """
    Calculează scorul minim pentru un interval de participanți dat.
    :param final_list:
    :param participant_start:
    :param participant_end:
    :return:
    """
    if participant_end < participant_start:
        return 0
    min, i = 11, participant_start
    score = getListOfScores(final_list)
    while i < participant_end + 1:
        if min > score[i]:
            min = score[i]
        i += 1
    return min


def WriteParticipantMultipleOf10(final_list, participant_start, participant_end):
    """
    Tipărește participanții dintr-un interval dat care au scorul multiplu de 10.
    :param final_list:
    :param participant_start:
    :param participant_end:
    :return:
    """
    if participant_end < participant_start:
        return 0
    scores, i = getListOfScores(final_list), participant_start
    while i < participant_end + 1:
        if int(scores[i] % 10) == 0:
            print(i)
        i += 1