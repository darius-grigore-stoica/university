//
// Created by Darius on 3/29/2023.
//

#include "Square.h"
#ifndef LAB5_REPO_H
#define LAB5_REPO_H


class Repo{
private:
    int nrElements;
    int capacity;
    Square* elements;
public:
    Repo();
    Repo(int nrElements, int capacity, Square* elements);
    ~Repo();
    //Returneaza numarul de elemente ale this
    int getNrElems();
    //Returneaza capacitatea ale this
    int getCapacity();
    /* Desc:Adauga element de tip Square, cu parametrii x si y, in lista de patrate
     * In: Parametrii x si y reprezentand valorile pe axa Oy a punctelor ce determina patratul
     * Out:
     * */
    void addSquare(int x, int y);
    //Afiseaza lista de patrate sub forma Patrat: latura
    void printSquares();
    //Dubleaza capacitatea listei de patrate, la momentul completarii acesteia
    void resize();
    //Returneaza elementul Patrat de pe pozitia pos data ca parametru
    Square getAt(int pos);
};

#endif //LAB5_REPO_H
