//
// Created by Darius on 5/7/2023.
//
#include "service.h"
#ifndef LAB9_UI_H
#define LAB9_UI_H
class UI{
private:
    Service s;
public:
    UI();
    ~UI();
    static void printMenu();
    void runMenu();
    void add();
    void update();
    void remove();
    void printRepo();
    void printMonede();
    void buy();

};
#endif //LAB9_UI_H
