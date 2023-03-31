//
// Created by Darius on 3/29/2023.
//
#include "Repo.h"
#include "Square.h"
#include <iostream>
using namespace std;

Repo::Repo() {
    this->nrElements = 0;
    this->capacity = 10;
    this->elements = new Square[this->capacity];
}

Repo::~Repo() {
    if(this->elements)
        delete[] this->elements;
}

Repo::Repo(int nrElements, int capacity, Square *elements) {
    this->capacity = capacity;
    this->nrElements = nrElements;
    this->elements = elements;
}

int Repo::getNrElems() {
    return this->nrElements;
}

int Repo::getCapacity() {
    return this->capacity;
}

void Repo::addSquare(int x, int y) {
    Square s = Square(x, y);
    if(this->nrElements == this->capacity)
        resize();
    this->elements[this->nrElements] = s;
    this->nrElements++;
}

void Repo::printSquares() {
    int i;
    for(i = 0; i < this->nrElements; i++)
        cout << this->elements[i] << " ";
}

void Repo::resize() {
    this->capacity *= 2;
    Square* newElems = new Square[this->capacity];
    int i;
    for (i = 0; i < this->nrElements; i++) {
        newElems[i] = this->elements[i];
    }
    delete[] elements;
    this->elements = newElems;
}

Square Repo::getAt(int pos) {
    if(pos > -1 && pos < this->nrElements)
        return this->elements[pos];
}

