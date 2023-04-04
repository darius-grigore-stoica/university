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

/*
 * 1    3
 * 2    2
 * 3    1
 * */
Square Repo::getAt(int pos) {
   queue<Square> copy = this->elements;
    int i;
    for(i = 0; i < pos; i++)
        copy.pop();
    if(pos > -1 && pos < this->nrElements)
        return copy.front();
    return {0, 0};
}

Square Repo::getMaxim(){
    Square max = Square(0, 0);
    queue<Square> copy = this->elements;
    while(!copy.empty()){
        Square front = copy.front();
        if(max.getArea() < front.getArea())
            max = front;
        copy.pop();
    }
    return max;
}

void Repo::getPositive() {
    queue<Square> copy = this->elements;
    while(!copy.empty()) {
        if (copy.front().isInFirst()) {
            cout << copy.front();
        }
        copy.pop();
    }
}

void Repo::getSequenceOfEquals(int &left, int &right) {
    int i, max;
    i = 0;
    max = -1;
    queue<Square> copy = this->elements;//declaram o copie a cozii de elemente
    while(i < this->elements.size()){
        bool equals = true;//declaram o variabila de validare a egalitatii intre elemente

        int left_c = i, right_c = i;//variabile intermediare, folosite pentru a determina lungimea secventei verificate curent
        while(!copy.empty() && equals){//cat timp avem elemente de comparat in coada si egalitate intre elementele din secventa curenta
            Square first, second;//preluam primul si al doilea element din ordine din coada
            first = copy.front();
            copy.pop();
            second = copy.front();
            if(!(first == second))//si daca nu sunt egale, negam variabila de validare
                equals = false;
            else right_c++;

        }

        if(right_c - left_c >= max) {//evalueam lungimea secventei curente prin comparare cu secventa maxima
            max = right_c - left_c;
            right = right_c;//iar după executarea pe toată lista, obtinem secventa cea mai lunga
            left = left_c;
        }
        i++;
    }
}

