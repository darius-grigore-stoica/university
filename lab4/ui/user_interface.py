from functionality.functionality import *

#####
##-----------AICI VOM DEFINII OPERATILE DE CITIRE/AFISARE
#####
def ui_AddNewParticipant(final_list):
    print("Adaugă scor pentru un nou participant")
    print("Lista de scoruri:\n")
    final_list.append(AddNewParticipant())
    print(final_list)

def ui_InsertScoreForGivenParticipant(final_list):
    print("Inserare scor pentru un participant\n")
    participant_index = int(input("Numarul de ordine al participantului al carui scor vrei sa il modifici:\n"))
    score_index = int(input("Pozitia scorului ce doresti a fi modificat:\n"))
    inserted_score = int(input("Valoarea noului scor:\n"))
    final_list = InsertScoreForGivenParticipant(final_list, participant_index, score_index - 1, inserted_score)
    print("Lista după adaugarea scorului:\n")
    print(final_list)

def ui_EraseScoreForGivenParticipant(final_list):
    print("Șterge scorul pentru un participant dat\n")
    participant_index = int(input("Numarul de ordine al participantului al carui scor doresti sa il stergi:\n"))
    score_index = int(input("Pozitia scorului ce doresti a fi sters:\n"))
    final_list = EraseScoreForGivenParticipant(final_list, participant_index, score_index - 1)
    print("Lista după stergerea scorului: ")
    print(final_list)

def ui_EraseScoreForInterval(final_list):
    print("Șterge scorul pentru un interval de participanți.\n")
    participant_start= int(input("Capatul din stanga al intervalului: \n"))
    participant_end = int(input("Capatul din dreapta al intervalului: \n"))
    final_list = EraseScoreForInterval(final_list, participant_start, participant_end)
    print("Lista după stergerea intervalului de participanti:\n")
    print(final_list)

def ui_SubstituteScore(final_list):
    print("Înlocuiește scorul de la un participant.")
    participant_index = int(input("Mumarul de ordine al participantului căruia îi inlocuiesti scorul: \n"))
    score_index = int(input("Pozitia scorului ce se doreste a fi inlocuit: \n"))
    substitute_score = int(input("Valoarea scorului dupa inlocuire: \n"))
    final_list = SubstituteScore(final_list, participant_index, score_index - 1, substitute_score)
    print("Lista dupa înlocuiește scorul de la un participant:\n")
    print(final_list)

def ui_WriteLowerScoreParticipant(final_list):
    print("Tipărește participanții care au scor mai mic decât un scor dat.")
    given_score = int(input("Scorul dat: "))
    print("Numerele de ordine a participantilor ce au un scor mai mic decat scorul dat:\n")
    WriteLowerScoreParticipant(final_list, given_score)

def ui_WriteSortedParticipant(scores):
    print("Tipărește participanții ordonat după scor")
    print("Numerele de ordine a participantilor in ordine descrescatoare:\n")
    WriteSortedParticipant(scores)

def ui_WriteGivenScoreSorted(final_list):
    print("Tipărește participanții cu scor mai mare decât un scor dat, ordonat după scor.\n")
    given_score = int(input("Scorul dat:"))
    WriteGivenScoreSorted(final_list, given_score)

def Menu():
    menu = "MENU\n"
    menu = menu + "1. Adaugă scor pentru un nou participant (ultimul participant)\n"
    menu = menu + "2. Inserare scor pentru un participant.\n"
    menu = menu + "3. Șterge scorul pentru un participant dat.\n"
    menu = menu + "4. Șterge scorul pentru un interval de participanți.\n"
    menu = menu + "5. Înlocuiește scorul de la un participant.\n"
    menu = menu + "6. Tipărește participanții care au scor mai mic decât un scor dat.\n"
    menu = menu + "7. Tipărește participanții ordonat după scor.\n"
    menu = menu + "8. Tipărește participanții cu scor mai mare decât un scor dat, ordonat după scor."
    return menu

def Program():
    final_list = [[], [5, 8, 3, 9, 10, 4, 6, 2, 8, 2], [1, 6, 7, 3, 4 ,8, 4 ,1, 6, 10], [1, 2, 3]]
    ruleaza = True
    scores = getListOfScores(final_list)
    while ruleaza == True:
        MyMenu = Menu()
        print(MyMenu)
        comanda = input("Introduceti comanda: ")
        if comanda == "1":
            ui_AddNewParticipant(final_list)
        elif comanda == "2":
            ui_InsertScoreForGivenParticipant(final_list)
        elif comanda == "3":
            ui_EraseScoreForGivenParticipant(final_list)
        elif comanda == "4":
            ui_EraseScoreForInterval(final_list)
        elif comanda == "5":
            ui_SubstituteScore(final_list)
        elif comanda == "6":
            ui_WriteLowerScoreParticipant(final_list)
        elif comanda == "7":
            ui_WriteSortedParticipant(scores)
        elif comanda == "8":
            ui_WriteGivenScoreSorted(final_list)
        else:
            print("Comanda invalida! Reincercati!")