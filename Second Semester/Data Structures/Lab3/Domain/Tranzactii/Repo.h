//
// Created by Darius on 3/28/2023.
//

#ifndef LAB2_REPO_H
#define LAB2_REPO_H
#include "../Colectie/Colection.h"
#include "Tranzactie.h"

class Repo{
private:
    Tranzactie* tranzactii;
    int nrTranzactii;
public:
    Repo();
    ~Repo();
    /*
     * Desc: Adauga tranzactii in vectorul alocat dinamic tranzactii
     * In:
     * @param:: id - parametru intreg, reprezinta id-ul tranzactiei adaugata
     * @param:: c - parametru de tip colectie care retine colectia cu care s-a efectual tranzactie
     * @param:: suma - parametru de tip TElem reprezinta suma ce trebuie platita
     * */
    void addTransaction(int id, Collection& c, TElem suma);
    /* Desc: Afiseaza toate tranzactiile din vectorul alocat dinamic tranzactii
     * In: Pozitia tranzactiei ce dorim a fi afisata din vectorul de tranzactii
     * */
    void printTranzactions(int poz);
};
#endif //LAB2_REPO_H
