//
// Created by Darius on 5/7/2023.
//
#include "../controller/service.h"
#ifndef LAB9_UI_H
#define LAB9_UI_H
class UI{
private:
    Service s;
public:
    UI();
    ~UI();
    void printMenu();
    void runMenu();
    void buy();
    void read();
    void add();
    void update();
    void remove();
};
#endif //LAB9_UI_H
