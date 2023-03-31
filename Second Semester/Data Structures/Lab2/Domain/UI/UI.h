//
// Created by Darius on 3/31/2023.
//
#include "../Tranzactii/Repo.h"
#include "../ATM/ATM.h"
#include "../Colectie/Colection.h"


#ifndef LAB2_UI_H
#define LAB2_UI_H

class UI{
private:
    Collection c;
    Repo t;
    ATM a;
public:
    UI();
    void printMenu();
    void runMenu();
    void retragere(int& i);
};
#endif //LAB2_UI_H
