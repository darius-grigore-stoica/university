//
// Created by Darius on 5/3/2023.
//
#include "repository.h"
#include "stdexcept"
#include "string.h"

Repo::Repo() {
    this->nrElems = 0;
    elems.reserve(10);
}

void Repo::add(const Produs &p) {
    this->elems.push_back(p);
    this->nrElems++;
}

Produs &Repo::getAt(int pos) {
    if(pos > -1 && pos < this->nrElems)
        return this->elems[pos];
    else throw std::invalid_argument("Pozitie invalida pentru extragere");
}

void Repo::update(const Produs &p, int pos) {
    if(pos > -1 && pos < this->nrElems)
        this->elems[pos] = p;
    else throw std::invalid_argument("Pozitie invalida pentru actualizare");
}

void Repo::remove(int pos) {
    if(pos > -1 && pos < this->nrElems) {
        this->nrElems--;
        this->elems.erase(this->elems.begin() + pos);
    }
    else throw std::invalid_argument("Pozitie invalida pentru stergere");
}

int Repo::size() {
    return this->nrElems;
}

ostream &operator<<(ostream &os, const Repo &r) {
    for(int i = 0; i < r.nrElems; i++)
        os << r.elems.at(i);
    return os;
}

Repo::~Repo() = default;


