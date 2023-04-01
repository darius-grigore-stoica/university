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
        cout << this->elements[i];
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

Square Repo::getMaxim() {
    Square max = Square();
    int i;
    for (i = 0; i < this->nrElements; ++i) {
        if(max.getArea() < this->elements[i].getArea())
            max = this->elements[i];
    }
    return max;
}

void Repo::getPositive(int *pos, int& size) {
    int i;
    for (i = 0; i < this->nrElements; ++i) {
        if(this->elements[i].isInFirst())
            pos[size++] = i;
    }
}

void Repo::getSequenceOfEquals(int &left, int &right) {
    int i;
    i = 0;
    int left_c = 0, right_c = 0;
    int max;
    max = -1;
    while(i < this->nrElements - 1){
        left_c = i;
        right_c = i + 1;
        if(this->elements[i] == this->elements[i + 1]) {
            right_c++;
        }
        if(right_c - left_c >= max){
            left = left_c - 1;
            right = right_c - 1;
            max = left - right;
        }
        i++;
    }
}

