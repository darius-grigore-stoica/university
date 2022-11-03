from functionality.functionality import *

#####
##-----------AICI VOM DEFINII OPERATILE DE CITIRE/AFISARE
#####
def ui_AddNewParticipant(final_list):
    print("Adaugă scor pentru un nou participant")
    print("Lista de scoruri:")
    final_list.append(AddNewParticipant())
    print(final_list)

def ui_InsertScoreForGivenParticipant(final_list):
    print("Inserare scor pentru un participant")
    participant_index = int(input("Numarul de ordine al participantului al carui scor vrei sa il modifici: "))
    score_index = int(input("Pozitia scorului ce doresti a fi modificat: "))
    inserted_score = int(input("Valoarea noului scor: "))
    final_list = InsertScoreForGivenParticipant(final_list, participant_index, score_index - 1, inserted_score)
    print("Lista după adaugarea scorului:")
    print(final_list)

def ui_EraseScoreForGivenParticipant(final_list):
    print("Șterge scorul pentru un participant dat")
    participant_index = int(input("Numarul de ordine al participantului al carui scor doresti sa il stergi: "))
    score_index = int(input("Pozitia scorului ce doresti a fi sters: "))
    final_list = EraseScoreForGivenParticipant(final_list, participant_index, score_index - 1)
    print("Lista după stergerea scorului: ")
    print(final_list)

def ui_EraseScoreForInterval(final_list):
    print("Șterge scorul pentru un interval de participanți.")
    participant_start= int(input("Capatul din stanga al intervalului: "))
    participant_end = int(input("Capatul din dreapta al intervalului: "))
    final_list = EraseScoreForInterval(final_list, participant_start, participant_end)
    print("Lista după stergerea intervalului de participanti:")
    print(final_list)

def ui_SubstituteScore(final_list):
    print("Înlocuiește scorul de la un participant.")
    participant_index = int(input("Mumarul de ordine al participantului căruia îi inlocuiesti scorul: "))
    score_index = int(input("Pozitia scorului ce se doreste a fi inlocuit: "))
    substitute_score = int(input("Valoarea scorului dupa inlocuire: "))
    final_list = SubstituteScore(final_list, participant_index, score_index - 1, substitute_score)
    print("Lista dupa înlocuiește scorul de la un participant:")
    print(final_list)

def ui_WriteLowerScoreParticipant(final_list):
    print("Tipărește participanții care au scor mai mic decât un scor dat.")
    given_score = int(input("Scorul dat: "))
    print("Participantii ce au un scor mai mic decat scorul dat:")
    WriteLowerScoreParticipant(final_list, given_score)

def ui_WriteSortedParticipant(scores):
    print("Tipărește participanții ordonat după scor")
    print("Numerele de ordine a participantilor in ordine descrescatoare:")
    WriteSortedParticipant(scores)

def ui_WriteGivenScoreSorted(final_list):
    print("Tipărește participanții cu scor mai mare decât un scor dat, ordonat după scor.")
    given_score = int(input("Scorul dat: "))
    WriteGivenScoreSorted(final_list, given_score)

def ui_WriteAvaregeScoreForInterval(final_list):
    print("Calculează media scorurilor pentru un interval dat")
    participant_start = int(input("Capatul din stanga al intervalului: "))
    participant_end = int(input("Capatul din dreapta al intervalului: "))
    print(WriteAvaregeScoreForInterval(final_list, participant_start, participant_end))

def ui_getMinimScoreForInterval(final_list):
    print("Calculează scorul minim pentru un interval de participanți dat.")
    participant_start = int(input("Capatul din stanga al intervalului: "))
    participant_end = int(input("Capatul din dreapta al intervalului: "))
    print("Minimul scorului obtinut de intervalul dat de participanti este:")
    print(getMinimScoreForInterval(final_list, participant_start, participant_end))

def ui_WriteParticipantMultipleOf10(final_list):
    print("Tipărește participanții dintr-un interval dat care au scorul multiplu de 10.")
    participant_start = int(input("Capatul din stanga al intervalului: "))
    participant_end = int(input("Capatul din dreapta al intervalului: "))
    print("Lista participantilor a căror scor este multiplu de 10 este:")
    WriteParticipantMultipleOf10(final_list, participant_start, participant_end)

def ui_FilterMultipleOfGivenNumber(final_list):
    print("Filtrare participanți care au scorul multiplu unui număr dat.")
    given_number = int(input("Numărul dat: "))
    print("Lista participantilor după filtrare:")
    print(FilterMultipleOfGivenNumber(final_list, given_number))

def ui_FilterParticipantWithLowerScore(final_list):
    print("Filtrare participanți care au scorul mai mic decât un scor dat.")
    given_score = int(input("Scorul dat: "))
    print("Lista participantilor după filtrare:")
    print(FilterParticipantWithLowerScore(final_list, given_score))


def Menu():
    menu = "MENIU\n"
    menu = menu + "1. Adaugă scor pentru un nou participant (ultimul participant)\n"
    menu = menu + "2. Inserare scor pentru un participant.\n"
    menu = menu + "3. Șterge scorul pentru un participant dat.\n"
    menu = menu + "4. Șterge scorul pentru un interval de participanți.\n"
    menu = menu + "5. Înlocuiește scorul de la un participant.\n"
    menu = menu + "6. Tipărește participanții care au scor mai mic decât un scor dat.\n"
    menu = menu + "7. Tipărește participanții ordonat după scor.\n"
    menu = menu + "8. Tipărește participanții cu scor mai mare decât un scor dat, ordonat după scor.\n"
    menu = menu + "9. Calculeaza media scorurilor pentru un interval dat\n"
    menu = menu + "10. Calculează scorul minim pentru un interval de participanți dat.\n"
    menu = menu + "11. Tipărește participanții dintr-un interval dat care au scorul multiplu de 10.\n"
    menu = menu + "12. Filtrare participanți care au scorul multiplu unui număr dat.\n"
    menu = menu + "13. Filtrare participanți care au scorul mai mic decât un scor dat.\n"
    menu = menu + "X. Ieșire"
    return menu

def Program():
    final_list = [[], [10, 10, 10], [5, 8, 3], [1, 6, 7, 10], [1, 2, 3]]
    scores = getListOfScores(final_list)

    ruleaza = True
    while ruleaza == True:
        MyMenu = Menu()
        print(MyMenu)
        comanda = input("Introduceti comanda: ")
        if comanda == "1":
            ui_AddNewParticipant(final_list)
            undo = 1
        elif comanda == "2":
            ui_InsertScoreForGivenParticipant(final_list)
            undo = 2
        elif comanda == "3":
            ui_EraseScoreForGivenParticipant(final_list)
            undo = 3
        elif comanda == "4":
            ui_EraseScoreForInterval(final_list)
            undo = 4
        elif comanda == "5":
            ui_SubstituteScore(final_list)
            undo = 5
        elif comanda == "6":
            ui_WriteLowerScoreParticipant(final_list)
            undo = 6
        elif comanda == "7":
            ui_WriteSortedParticipant(scores)
            undo = 7
        elif comanda == "8":
            ui_WriteGivenScoreSorted(final_list)
            undo = 8
        elif comanda == "9":
            ui_WriteAvaregeScoreForInterval(final_list)
            undo = 9
        elif comanda == "10":
            ui_getMinimScoreForInterval(final_list)
            undo = 10
        elif comanda == "11":
            ui_WriteParticipantMultipleOf10(final_list)
            undo = 11
        elif comanda == "12":
            ui_FilterMultipleOfGivenNumber(final_list)
            undo = 12
        elif comanda == "13":
            ui_FilterParticipantWithLowerScore(final_list)
            undo = 13
        elif (comanda == "X" or comanda == "x"):
            ruleaza = False
        else:
            print("Comanda invalida! Reincercati!")