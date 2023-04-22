//
// Created by Darius on 4/17/2023.
//
#include "../service/Service.h"
#ifndef LAB8_UI_H
#define LAB8_UI_H
class UI{
private:
    Service s;
    Service u;
    const int CAP = 50;
public:
    UI();
    static void printMenu();
    void runMenu();
    void add();
    void update();
    void print();
    void stats();
    void filter();
    void undo();
};
#endif //LAB8_UI_H
