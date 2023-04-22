//
// Created by Darius on 4/12/2023.
//
#include "Repo.h"

Repo::Repo() {
    this->capacity = 10;
    this->elems = new Cheltuieli[this->capacity];
    this->nrElems = 0;
}

Repo::Repo(int capacity, Cheltuieli *elems, int nrElems) {
    this->capacity = capacity;
    this->elems = elems;
    this->nrElems = nrElems;
}

Repo::Repo(const Repo &r) {
    this->capacity = r.capacity;
    int i;
    for(i = 0; i < r.nrElems; i++)
        this->elems[i] = r.elems[i];
    this->nrElems = r.nrElems;
}

Repo::~Repo() {
    if (this->elems)
        delete[] this->elems;
}

void Repo::add(const Cheltuieli &elem) {
    if (this->nrElems == this->capacity)
        resize();
    this->elems[this->nrElems] = elem;
    this->nrElems++;
}

void Repo::resize() {
    this->capacity *= 2;
    Cheltuieli *newElems = new Cheltuieli[this->capacity];
    for (int i = 0; i < this->nrElems; i++)
        newElems[i] = this->elems[i];
    delete[] this->elems;
    this->elems = newElems;
}

void Repo::update(int pos, Cheltuieli &elem) {
    this->elems[pos] = elem;
}

void Repo::remove(int pos) {
    int i;
    for (i = pos; i < this->nrElems - 1; i++)
        this->elems[i] = this->elems[i + 1];
    this->nrElems--;
}

Cheltuieli Repo::getAt(int pos) {
    if (pos > -1 && pos < this->nrElems)
        return this->elems[pos];
}

int Repo::getSize() {
    return this->nrElems;
}

Repo &Repo::operator=(const Repo &r) {
    this->nrElems = r.nrElems;
    this->capacity = r.capacity;
    int i;
    for (i = 0; i < this->nrElems; i++)
        this->elems[i] = r.elems[i];
    return *this;
}

Cheltuieli& Repo::swap(int i, int j) {
    Cheltuieli aux = this->elems[i];
    this->elems[i] = this->elems[j];
    this->elems[j] = aux;
    return this->elems[i];
}

ostream &operator<<(ostream &os, Repo &r) {
    int i;
    int size;
    size = r.getSize();
    for(i = 0; i < size; i++){
        Cheltuieli c = r.getAt(i);
        cout << c;
    }
}



