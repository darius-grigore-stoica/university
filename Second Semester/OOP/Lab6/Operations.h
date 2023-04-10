//
// Created by Darius on 4/5/2023.
//

#include "Square.h"
#include "Repo.h"
#ifndef STIVE_OPERATIONS_H
#define STIVE_OPERATIONS_H
class Operations { ;
public:
    Operations();
//Determina cel mai mare patrat din lista de patrate
    Square getMaxim(Repo& r);

/*
 * Desc: Afiseaza toate patratele aflate complet in cadranul I al cercului geometrie
 * */
    void getPositive(Repo& r);

/* Desc: Identifica cea mai lunga secventa de patrate egale
 * In:
 * @param:: left, right - Intregi care retin indexul la care incepe, respectiv la care se termina, secventa identificata
 * Nota: Spune despre două patrate că sunt egale dacă punctele lor sunt identice
 * */
    void getSequenceOfEquals(Repo& r, int &left, int &right);

//Returneaza elementul Patrat de pe pozitia pos data ca parametru
    Square getAt(Repo& r, int pos);
};
#endif //STIVE_OPERATIONS_H
