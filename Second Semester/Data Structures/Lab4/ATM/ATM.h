//
// Created by Darius on 3/25/2023.
//

#ifndef LAB2_ATM_H
#define LAB2_ATM_H

#include "../Tranzactii/Repo.h"
#include "../Colectie/Colection.h"

class ATM {
private:
    Collection colectie;
    Repo tranzactii;
public:
    ATM();//Constructor general
    ATM(Collection& c, Repo& t);//Constructor implicit
    ~ATM();//Deconstructor
    /*
     * Desc: Returneaza true in cazul in care suma primita ca parametru poate fi platita din ATM folosind bancnote din parametrul c de tip colectie
     * In:
     * @param:: c - colectia din care se extrag bancnotele
     * @param:: sum - suma care trebuie platita de ATM
     * @param:: newCollection - parametru dat prin referinta care retine colectia nou formata cu bancnotele din c cu care se efectueaza plata
     * @param:: iteratie - parametru de tip intreg care retine iteratie curenta la care se afla algoritmul recursiv
     * Out: true - in cazul in care se poate efectua plata, false in rest
     * */
    bool backtracking(Collection& c, int sum, Collection& newCollection, int iteratie);
};
#endif //LAB2_ATM_H
