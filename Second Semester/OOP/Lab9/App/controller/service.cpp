//
// Created by Darius on 5/7/2023.
//
#include "service.h"
#include "stdexcept"

Service::Service() {
    this->r = Repo();
}

Service::Service(Repo &r) {
    this->r = r;
}

Service::Service(const Service &s) {
    this->r = s.r;
}

Service::~Service() {}

void Service::add(float pret, char *cod, char *nume) {
    Produs p = Produs(pret, cod, nume);
    this->r.add(p);
}

void Service::update(float pret, char *cod, char *nume, int pos) {
    if(pos > -1 && pos < this->r.size()) {
        Produs p = Produs(pret, cod, nume);
        this->r.update(p, pos);
    }
    else throw std::invalid_argument("Pozitie invalida pentru actualizare");
}

void Service::remove(int pos) {
    if(pos > -1 && pos < this->r.size()) {
        this->r.remove(pos);
    }
    else throw std::invalid_argument("Pozitie invalida pentru stergere");
}

Produs &Service::getAt(int pos) {
    if(pos > -1 && pos <= this->r.size()) {
        this->r.getAt(pos);
    }
    else throw std::invalid_argument("Pozitie invalida");
}

int Service::size() {
    return this->r.size();
}

ostream &operator<<(ostream &os, const Service &s) {
    cout << s.r;
    return os;
};

