//
// Created by Darius on 4/14/2023.
//
#include "Service.h"
#include <string.h>

Service::Service() {
    this->r = Repo();
}

Service::Service(Repo &r) {
    this->r = r;
}

Service::Service(const Service &s) {
    this->r = s.r;
}

Service::~Service() {
}

void Service::add(int nrApartament, int suma, char *tipul) {
    Cheltuieli elem = Cheltuieli(nrApartament, suma, tipul);
    this->r.add(elem);
}

void Service::update(int pos, int nrApartament, int suma, char *tipul) {
    Cheltuieli elem = Cheltuieli(nrApartament, suma, tipul);
    this->r.update(pos, elem);
}

void Service::remove(int pos) {
    this->r.remove(pos);
}

Cheltuieli Service::getAt(int pos) {
    return r.getAt(pos);
}

int Service::getSize() {
    return r.getSize();
}

int Service::sum(const char *atr) {
    int sum = 0;
    int i;
    int size = this->r.getSize();
    for (i = 0; i < size; i++) {
        Cheltuieli c;
        c = this->r.getAt(i);
        if (strcmp(c.getTipul(), atr) == 0)
            sum += c.getSuma();
    }
    return sum;
}

Cheltuieli Service::max(int nrApartament) {
    int max = -1, pos = -1;
    int size;
    size = this->r.getSize();
    int i;
    for (i = 0; i < size; i++) {
        Cheltuieli c;
        c = this->r.getAt(i);
        if (c.getNrApartament() == nrApartament) {
            int suma;
            suma = c.getSuma();
            if (suma > max) {
                max = suma;
                pos = i;
            }
        }
    }
    if (pos > -1)
        return this->r.getAt(pos);
    else { return {-1, -1, ""}; }
}

Repo Service::sort(char *atr) {
    Repo rep = Repo();

    int p;
    for (p = 0; p < this->r.getSize(); p++) {
        Cheltuieli c = Cheltuieli();
        c = this->r.getAt(p);
        if (strcmp(c.getTipul(), atr) == 0)
            rep.add(c);
    }

    int i, j;
    int size;
    size = rep.getSize();
    for (i = 0; i < size; i++)
        for (j = 0; j < size; j++)
            if (rep.getAt(i) > rep.getAt(j))
                rep.swap(i, j);
    return rep;
}

Cheltuieli &Service::swap(int i, int j) {
    return this->r.swap(i, j);
}

Service &Service::operator=(const Service &s) {
    this->r = s.r;
}

Service Service::filterInteger(int atr) {
    Service serv = Service();
    int size = this->r.getSize();
    int i;
    for (i = 0; i < size; i++) {
        Cheltuieli c = this->r.getAt(i);
        if (atr > c.getSuma())
            serv.add(c.getNrApartament(), c.getSuma(), c.getTipul());
    }
    return serv;
}

Service Service::filterChar(char* atr) {
    Service serv = Service();
    int size = this->r.getSize();
    int i;
    for (i = 0; i < size; i++) {
        Cheltuieli c = this->r.getAt(i);
        if (strcmp(atr, c.getTipul()) == 0)
            serv.add(c.getNrApartament(), c.getSuma(), c.getTipul());
    }
    return serv;
}

ostream &operator<<(ostream &os, Service &s) {
    int size;
    int i;
    size = s.getSize();
    for(i = 0; i < size; i++){
        Cheltuieli c = s.getAt(i);
        os << c;
    }
}




