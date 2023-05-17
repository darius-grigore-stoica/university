//
// Created by Darius on 3/23/2023.
//
#include "Tranzactie.h"

Tranzactie::Tranzactie() {
    this->idTransaction = id;
    this->id++;
    this->elements = Collection();
    this->suma = 0;
}

Tranzactie::Tranzactie(Collection& elements, TElem suma) {
    this->idTransaction = id;
    id++;
    this->elements = elements;
    this->suma = suma;
}

Tranzactie::~Tranzactie()= default;

ostream& operator<<(ostream& os, Tranzactie& t){
    os << t.elements;
    os << "Suma: " << t.suma << "\n";
}


Tranzactie& Tranzactie::operator=(const Tranzactie &t) {
    this->idTransaction = t.idTransaction;
    this->elements = t.elements;
    this->suma = t.suma;
}

bool Tranzactie::operator==(const Tranzactie &t) {
    if(this->idTransaction != t.idTransaction)
        return false;
    if(this->suma != t.suma)
        return false;
    if(!(this->elements == t.elements))
        return false;
    return true;
}
