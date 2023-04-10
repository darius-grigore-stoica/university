//
// Created by Darius on 3/29/2023.
//
#include "Repo.h"
#include "Square.h"
#include <iostream>
#include <utility>
using namespace std;

Repo::Repo() {
    this->nrElements = 0;
    this->capacity = 10;
    queue<Square> q;
    this->elements = q;
}

Repo::~Repo() = default;

Repo::Repo(int nrElements, int capacity, queue<Square> elements) {
    this->capacity = capacity;
    this->nrElements = nrElements;
    this->elements = std::move(elements);
}

int Repo::getNrElems() const {
    return this->nrElements;
}

int Repo::getCapacity() const {
    return this->capacity;
}

void Repo::addSquare(int x, int y) {
    Square s = Square(x, y);
    this->elements.push(s);
    this->nrElements++;
}

void Repo::printSquares() {
    queue<Square> copy = this->elements;
    while(!copy.empty()){
        cout << copy.front();
        copy.pop();
    }
}

queue<Square> Repo::getElems(){
    return this->elements;
}
Square Repo::getAt(int pos) {
    queue<Square> copy = this->elements;
    int i;
    for(i = 0; i < pos; i++)
        copy.pop();
    if(pos > -1 && pos < this->nrElements)
        return copy.front();
    return {0, 0};
}

