//
// Created by Darius on 3/29/2023.
//
#include "UI.h"

void UI::addSquare() {
    int p1, p2;
    cout << "Introduti punctele ce determina patratul:\n";
    cin >> p1 >> p2;
    this->repo.addSquare(p1, p2);
}

void UI::printSquares() {
    this->repo.printSquares();
}

void UI::printMenu() {
    cout << "1. Adauga un patrat nou in lista\n";
    cout << "2. Afiseaza toate patratele\n";
    cout << "0. Iesire din aplicatie\n";
}

void UI::runMenu() {
    while(true){
        this->printMenu();
        int option;
        cout << "Introduceti optiunea:";
        cin >> option;
        if(option == 1)
            this->addSquare();
        else if(option == 2)
            this->printSquares();
        else if(option == 0)
            break;
    }
}

