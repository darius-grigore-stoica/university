//
// Created by Darius on 3/31/2023.
//
#include "Repo.h"
#include "ATM.h"
#include "Colection.h"
#include <fstream>
#include <sstream>
#ifndef LAB2_UI_H
#define LAB2_UI_H

class UI{
private:
    Collection c;
    Repo t;
    ATM a;
public:
    UI();
    ~UI();
    static void printMenu();
    void runMenu();
    void retragere(int& i);
    void printTranzacti();
};
#endif //LAB2_UI_H
