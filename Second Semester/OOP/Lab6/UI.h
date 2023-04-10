//
// Created by Darius on 3/29/2023.
//
#include "Repo.h"
#include "Operations.h"
#include <iostream>
using namespace std;
#ifndef LAB5_UI_H
#define LAB5_UI_H

class UI{
private:
    Repo repo;
    Operations operations;
public:
    UI();
    UI(Repo& r);
    void addSquare();
    void printSquares();
    void getMaxim();
    void getPositive();
    void SequenceOfEquals();
    static void printMenu();
    void runMenu();
};

#endif //LAB5_UI_H
