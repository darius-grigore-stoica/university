//
// Created by Darius on 5/7/2023.
//
#include "service.h"
#include "stdexcept"
#include <cstring>

Service::Service() {
    this->r = Repo();
    this->monede.reserve(10);
    this->monede.push_back(5);
    this->monede.push_back(5);
    this->monede.push_back(10);
    this->monede.push_back(10);
    this->monede.push_back(10);
    this->monede.push_back(50);
    this->monede.push_back(50);
    this->monede.push_back(50);
    this->monede.push_back(50);
}

Service::Service(Repo &r, vector<int> monede) {
    this->r = r;
    this->monede.reserve(monede.size());
    for (int i = 0; i < 10; i++)
        this->monede.push_back(monede.at(i));
}

Service::Service(const Service &s) {
    this->r = s.r;
    this->monede.reserve(s.monede.size());
    for (int i = 0; i < 10; i++)
        this->monede.push_back(s.monede.at(i));
}

Service::~Service() = default;

void Service::add(float pret, char *cod, char *nume) {
    Produs p = Produs(pret, cod, nume);
    this->r.add(p);
}

void Service::update(float pret, char *cod, char *nume, int pos) {
    if (pos > -1 && pos < this->r.size()) {
        Produs p = Produs(pret, cod, nume);
        this->r.update(p, pos);
    } else throw std::invalid_argument("Pozitie invalida pentru actualizare");
}

void Service::remove(int pos) {
    if (pos > -1 && pos < this->r.size()) {
        this->r.remove(pos);
    } else throw std::invalid_argument("Pozitie invalida pentru stergere");
}

Produs &Service::getAt(int pos) {
    if (pos > -1 && pos <= this->r.size()) {
        return this->r.getAt(pos);
    } else throw std::invalid_argument("Pozitie invalida");
}

int Service::size() {
    return this->r.size();
}

float Service::buy(char *nume, int bancnote) {
    //selectam produsul cu numele dat ca parametru si cu pretul mai mic decat bancnote
    int pos = findPosition(nume, bancnote);
    if (pos > -1) {
        Produs product = this->r.getAt(pos);

        //calculam restul pe care trebuie sa il oferim
        float rest = bancnote - product.getPret();

        //stergem produsul din lista de produse
        this->r.remove(pos);

        //verificam daca restul poate fi platit, caz in care il returnam
        if (suma() >= rest * 10)
            return rest;
        else return -1;
    } else return -1;
}

ostream &operator<<(ostream &os, const Service &s) {
    cout << s.r;
    return os;
}

int Service::findPosition(char *nume, int bancnote) {
    int pos = -1;
    int i = 0;
    while (i < this->r.size() && pos == -1) {
        if (strcmp(this->r.getAt(i).getNume(), nume) == 0)
            if (bancnote >= this->r.getAt(i).getPret())
                pos = i;
        i++;
    }
    return pos;
}

int Service::suma() {
    int suma = 0;
    for (int p: this->monede)
        suma += p;
    return suma;
}

void Service::printMonede() {
    for (int p: this->monede)
        cout << p << endl;
}
