//
// Created by Darius on 5/7/2023.
//
#include "service.h"
#include "stdexcept"
#include <string.h>

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

Service::~Service() {}

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
        this->r.getAt(pos);
    } else throw std::invalid_argument("Pozitie invalida");
}

int Service::size() {
    return this->r.size();
}

int Service::buy(char *nume, int bancnote) {
    int pos = -1;
    int i = 0;
    while (i < this->r.size()) {
        if (strcmp(this->r.getAt(i).getNume(), nume) == 0) {
            if (bancnote >= this->r.getAt(i).getPret()) {
                pos = i;
                break;
            }
        }
        i++;
    }
    Produs p = this->r.getAt(pos);
    int rest = bancnote - p.getPret();
    this->r.remove(pos);
    int suma = 0;
    for (int p = 0; p < this->monede.size(); p++)
        suma += this->monede.at(p);
    if (suma >= rest * 10)
        return rest;
    else return -1;
}

ostream &operator<<(ostream &os, const Service &s) {
    cout << s.r;
    return os;
}