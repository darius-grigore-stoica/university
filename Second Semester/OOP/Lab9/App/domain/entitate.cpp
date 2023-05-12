//
// Created by Darius on 5/3/2023.
//
#include "entitate.h"
#include <string.h>

Produs::Produs() {
    this->pret = -1;
    this->nume = new char[256];
    this->cod = new char[256];
}

Produs::Produs(int pret, char *cod, char *nume) {
    this->pret = pret;
    this->nume = new char[strlen(nume) + 1];
    strcpy(this->nume,  nume);
    this->cod = new char[strlen(cod) + 1];
    strcpy(this->cod, cod);
}

Produs::Produs(const Produs &p) {
    this->pret = p.pret;
    this->nume = new char[strlen(p.nume) + 1];
    strcpy(this->nume,  p.nume);
    this->cod = new char[strlen(p.cod) + 1];
    strcpy(this->cod, p.cod);
}

Produs::~Produs() {
    if(this->nume)
        delete[] this->nume;
    this->nume = nullptr;
    if(this->cod)
        delete[] this->cod;
    this->cod = nullptr;
}

Produs& Produs::operator=(const Produs &p) {
    this->pret = p.pret;
    this->nume = new char[strlen(p.nume) + 1];
    strcpy(this->nume,  p.nume);
    this->cod = new char[strlen(p.cod) + 1];
    strcpy(this->cod, p.cod);
    return *this;
}

float Produs::getPret() {
    return this->pret;
}

char *Produs::getCod() {
    return this->cod;
}

char *Produs::getNume() {
    return this->nume;
}

void Produs::setPret(int pret) {
    this->pret = pret;
}

void Produs::setCod(char *cod) {
    if(this->cod)
        strcpy(this->cod, cod);
    else {
        this->cod = new char[strlen(cod) + 1];
        strcpy(this->cod, cod);
    }
}

void Produs::setNume(char *nume) {
    if(this->nume)
        strcpy(this->nume, nume);
    else {
        this->nume = new char[strlen(nume) + 1];
        strcpy(this->nume, nume);
    }
}

bool Produs::operator==(const Produs &p) {
    if(strcmp(this->cod, p.cod) == 0)
        if(strcmp(this->nume, p.nume) == 0)
            if(this->pret == p.pret)
                return true;
    return false;
}

ostream &operator<<(ostream &os, const Produs &p) {
    os << "Cod: " << p.cod << endl << "Nume: " << p.nume << endl << "Pret: " << p.pret << endl;
    return os;
}

