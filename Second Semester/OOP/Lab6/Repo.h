//
// Created by Darius on 3/29/2023.
//

#include "Square.h"
#include <queue>
#ifndef LAB5_REPO_H
#define LAB5_REPO_H


class Repo{
private:
    int nrElements;
    int capacity;
    queue<Square> elements;
public:
    Repo();
    Repo(int nrElements, int capacity, queue<Square> elements);
    ~Repo();
    //Returneaza numarul de elemente ale this
    int getNrElems() const;
    //Returneaza capacitatea ale this
    int getCapacity() const;
    /* Desc:Adauga element de tip Square, cu parametrii x si y, in lista de patrate
     * In: Parametrii x si y reprezentand valorile pe axa Oy a punctelor ce determina patratul
     * Out:
     * */
    void addSquare(int x, int y);
    //Afiseaza lista de patrate sub forma Patrat: latura
    void printSquares();
    //Determina cel mai mare patrat din lista de patrate
    Square getMaxim();
    /*
     * Desc: Afiseaza toate patratele aflate complet in cadranul I al cercului geometrie
     * */
    void getPositive();
    /* Desc: Identifica cea mai lunga secventa de patrate egale
     * In:
     * @param:: left, right - Intregi care retin indexul la care incepe, respectiv la care se termina, secventa identificata
     * Nota: Spune despre două patrate că sunt egale dacă punctele lor sunt identice
     * */
    void getSequenceOfEquals(int& left, int& right);
    //Returneaza elementul Patrat de pe pozitia pos data ca parametru
    Square getAt(int pos);
};

#endif //LAB5_REPO_H
