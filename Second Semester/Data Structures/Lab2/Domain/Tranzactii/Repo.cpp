//
// Created by Darius on 3/28/2023.
//
#include "Repo.h"
#include "Tranzactie.h"

Repo::Repo() {
    this->nrTranzactii = 0;
    this->tranzactii = new Tranzactie[10];
}

Repo::~Repo() {
    if(this->tranzactii)
        delete[] this->tranzactii;
    this->tranzactii = NULL;
}

/*
 * Desc: Adaugarea unei tranzacti in lista de tranzactii
 * In: Colectia c si TElem suma
 * Out: */
void Repo::addTransaction(int id, Collection& c, TElem suma) {
    Tranzactie t = Tranzactie(id, c, suma);
    this->tranzactii[this->nrTranzactii] = t;
    this->nrTranzactii++;
}

/*
 * In: poz - reprezentand pozitia in lista de tranzactii a tranzactiei pentru afisare
 * */
void Repo::printTranzactions(int poz) {
    if(poz > -1 && poz < this->nrTranzactii)
        cout << this->tranzactii[poz] << "\n";
}

