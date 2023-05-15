//
// Created by Darius on 5/7/2023.
//
#include "UI.h"
#include <iostream>
using namespace std;

UI::UI() {
    this->s = Service();
}

void UI::printMenu() {
    cout << "1. Adauga produs\n";
    cout << "2. Actualizeaza produs\n";
    cout << "3. Sterge produs\n";
    cout << "4. Afiseaza toate produsele\n";
    cout << "5. Cumpara produs\n";
    cout << "6. Arata monedele din tonomat\n";
    cout << "0. Iesire din aplicatie\n";
}

void UI::runMenu() {
    while(true){
        this->printMenu();
        int option;
        cout << "Introduceti optiunea:\n";
        cin >> option;
        if(option == 1)
            add();
        else if(option == 2)
            update();
        else if(option == 3)
            remove();
        else if(option == 4)
            printRepo();
        else if(option == 5)
            buy();
        else if(option == 6)
            printMonede();
        else if(option == 0)
            break;
        else cout << "Incercati alta comanda!\n";
    }
}

void UI::buy() {
    cout << "Introduceti numele produsului:\n";
    char* nume = new char[256];
    cin.ignore();
    cin.getline(nume, 256);

    cout << "Introduceti bancnotele:\n";
    int bancnote;
    cin >> bancnote;

    float rest = this->s.buy(nume, bancnote);
    if(rest > -1)
        cout << "Restul este: " << rest << endl;
    else cout << "Nu se poate cumpara!\n";
}

void UI::add() {
    float pret;
    cout << "Pret:";
    cin >> pret;

    char* cod = new char[256];
    cout << "Cod:";
    cin.ignore();
    cin.getline(cod, 256);

    char* nume = new char[256];
    cout << "Nume:";
    cin.getline(nume, 256);

    this->s.add(pret, cod, nume);
}

void UI::update() {

    float pret;
    cout << "Pret:";
    cin >> pret;

    char* cod = new char[256];
    cout << "Cod:";
    cin.ignore();
    cin.getline(cod, 256);

    cout << "Nume:";
    char* nume = new char[256];
    cin.getline(nume, 256);

    int pos;
    cout << "Pozitia pentru actualizare:";
    cin >> pos;

    this->s.update(pret, cod, nume, pos);
}

void UI::remove() {
    int pos;
    cout << "Pozitia pentru stergere:";
    cin >> pos;
    this->s.remove(pos);
}

void UI::printRepo() {
    if(this->s.size() > 0)
        cout << s;
    else cout << "Nu exista elemente in lista de produse\n";
}

void UI::printMonede() {
    this->s.printMonede();
}

UI::~UI()= default;
