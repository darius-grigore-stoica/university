//
// Created by Darius on 3/29/2023.
//
#include "UI.h"

UI::UI() {
    this->repo = Repo();
}

UI::UI(Repo &r) {
    this->repo = r;
}

void UI::addSquare() {
    int p1, p2;
    cout << "Introduti punctele ce determina patratul:\n";
    cin >> p1 >> p2;
    this->repo.addSquare(p1, p2);
}

void UI::printSquares() {
    this->repo.printSquares();
}

void UI::getMaxim() {
    cout << "Patratul maxim este: \n";
    Square max = this->repo.getMaxim();
    cout << max;
}

void UI::getPositive() {
    cout << "Patratele aflate in primul cadran sunt:\n";
    this->repo.getPositive();
}

void UI::SequenceOfEquals() {
    int left = 0, right = -1;
    this->repo.getSequenceOfEquals(left, right);
    cout << "Cea mai lunga secventa cu numere egale este:\n";
    int i;
    for(i = left; i <= right; i++){
        Square res = this->repo.getAt(i);
        cout << res;
    }
    cout << endl;
}

void UI::printMenu() {
    cout << "1. Adauga un patrat nou in lista\n";
    cout << "2. Afiseaza toate patratele\n";
    cout << "3. Determina cel mai mare patrat\n";
    cout << "4. Afiseaza patratele din primul cadran\n";
    cout << "5. Afiseaza cea mai lunga secventa cu elemente egale\n";
    cout << "0. Iesire din aplicatie\n";
}

void UI::runMenu() {
    while(true){
        this->printMenu();
        int option;
        cout << "Introduceti optiunea:\n";
        cin >> option;
        if(option == 1)
            this->addSquare();
        else if(option == 2)
            this->printSquares();
        else if(option == 3)
            this->getMaxim();
        else if(option == 4)
            this->getPositive();
        else if(option == 5)
            this->SequenceOfEquals();
        else if(option == 0)
            break;
        else cout << "Incercati alta comanda!\n";
    }
}
