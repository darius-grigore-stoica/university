//
// Created by Darius on 4/12/2023.
//
#include "Cheltuieli.h"
#include <string.h>

Cheltuieli::Cheltuieli() {
    this->nrApartament = -1;
    this->suma = -1;
    this->tipul = new char[256];
}

Cheltuieli::Cheltuieli(int nrApartament, int suma, const char* tipul) {
    this->nrApartament = nrApartament;
    this->suma = suma;
    this->tipul = new char[strlen(tipul) + 1];
    strcpy(this->tipul, tipul);
}

Cheltuieli::Cheltuieli(const Cheltuieli &c) {
    this->nrApartament = c.nrApartament;
    this->suma = c.suma;
    this->tipul = new char[strlen(c.tipul) + 1];
    strcpy(this->tipul, c.tipul);
}

int Cheltuieli::getNrApartament() {
    return this->nrApartament;
}

void Cheltuieli::setNrApartament(int nrApartament) {
    this->nrApartament = nrApartament;
}

bool Cheltuieli::operator==(const Cheltuieli &c) {
    if(this->nrApartament == c.nrApartament)
        if(this->suma == c.suma)
            if(strcmp(this->tipul, c.tipul) == 0)
                return true;
    return false;
}

int Cheltuieli::getSuma(){
    return this->suma;
}

char *Cheltuieli::getTipul() {
    return this->tipul;
}

void Cheltuieli::setSuma(int suma) {
    this->suma = suma;
}

void Cheltuieli::setTipul(const char *tipul) {
    this->tipul = new char[strlen(tipul) + 1];
    strcpy(this->tipul, tipul);
}

Cheltuieli::~Cheltuieli() {
    if(this->tipul)
        delete[] this->tipul;
    this->tipul = nullptr;
}


