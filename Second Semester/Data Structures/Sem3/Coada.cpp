//
// Created by Darius on 4/6/2023.
//
#include <stdexcept>
#include "Coada.h"

Coada::Coada() {
    this->start = 0;
    this->end = -1;
    this->capacity = 10;
    this->elements = new TElem [this->capacity];
}

Coada::~Coada() {
    if(this->elements)
        delete[] this->elements;
}

void Coada::add(TElem elem) {
    if(this->end == this->capacity - 1)
        this->resize();
    else {
        this->end++;
        this->elements[this->end] = elem;
    }
}

void Coada::resize() {
    this->capacity *= 2;
    TElem *newElems = new TElem[this->capacity];
    int i;
    for(i = start; i < end; i++)
        newElems[i] = this->elements[i];
    delete[] this->elements;
    this->elements = newElems;
}

bool Coada::isEmpty() {
    if(this->end < this->start)
        return true;
    return false;
}

int Coada::size() {
    return this->end - this->start + 1;
}

TElem Coada::remove() {
    if(isEmpty())
        throw std::invalid_argument("Coada este vida");
    else{
        TElem elem = this->elements[this->start];
        this->start++;
        return elem;
    }
}
