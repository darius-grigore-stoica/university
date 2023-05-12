//
// Created by Darius on 5/7/2023.
//
#include "../controller/service.h"
#include "../colectie/Colection.h"
#ifndef LAB9_UI_H
#define LAB9_UI_H
class UI{
private:
    Service s;
    Collection c;
public:
    UI();
    ~UI();
    void printMenu();
    void runMenu();
};
#endif //LAB9_UI_H
