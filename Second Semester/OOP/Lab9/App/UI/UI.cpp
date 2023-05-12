//
// Created by Darius on 5/7/2023.
//
#include "UI.h"
#include <iostream>
using namespace std;

UI::UI() {
    this->s = Service();
    this->c.add(5);
    this->c.add(5);
    this->c.add(5);
    this->c.add(1);
    this->c.add(1);
    this->c.add(1);
    this->c.add(1);
    this->c.add(1);
    this->c.add(1);
    this->c.add(1);
    this->c.add(0.5);
    this->c.add(0.5);
    this->c.add(0.5);
    this->c.add(0.5);
    this->c.add(0.5);
}

void UI::printMenu() {
    cout << "1. Cumpara produs\n";
    cout << "0. Iesire din aplicatie\n";
}

void UI::runMenu() {
    while(true){
        this->printMenu();
        int option;
        cout << "Introduceti optiunea:\n";
        cin >> option;
        if(option == 1){
        }
        else if(option == 0)
            break;
        else cout << "Incercati alta comanda!\n";
    }
}
UI::~UI()= default;
