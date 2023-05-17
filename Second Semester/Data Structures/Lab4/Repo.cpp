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
    this->tranzactii = nullptr;
}
int Repo::getNrTrazanctii() {
    return this->nrTranzactii;
}

/*
 * Desc: Adaugarea unei tranzacti in lista de tranzactii
 * In: Colectia c si TElem suma
 * Out: */
void Repo::addTransaction(Collection& c, TElem suma) {
    Tranzactie t = Tranzactie(c, suma);
    this->tranzactii[this->nrTranzactii] = t;
    this->nrTranzactii++;
}

Tranzactie &Repo::getAt(int pos) {
    if(pos > -1 && pos < this->nrTranzactii)
        return this->tranzactii[pos];
}

ostream &operator<<(ostream &os, Repo &r) {
    int i;
    for(i = 0; i < r.nrTranzactii; i++)
        os << r.getAt(i);
    return os;
}

void Repo::add(const Tranzactie &t) {
    this->tranzactii[this->nrTranzactii] = t;
    this->nrTranzactii++;
}

