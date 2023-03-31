//
// Created by Darius on 3/29/2023.
//
#include "Repo.h"
#include <iostream>
using namespace std;
#ifndef LAB5_UI_H
#define LAB5_UI_H

class UI{
private:
    Repo repo;
public:
    void addSquare();
    void printSquares();
    void printMenu();
    void runMenu();
};

#endif //LAB5_UI_H
