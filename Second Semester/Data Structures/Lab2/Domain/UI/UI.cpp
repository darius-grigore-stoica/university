//
// Created by Darius on 3/31/2023.
//
#include "UI.h"

void UI::printMenu() {
    cout << "1. Retragere\n";
    cout << "0. Iesire\n";
}

void UI::retragere(int& i){
    int suma = 0;
    do{
        cout << "Introduceti suma pentru retragere:\n";
        cin >> suma;
    } while(suma <= 0);

    Collection newC = Collection();
    this->c.sort();
    bool res;
    res = this->a.backtracking(this->c, suma, newC, 0);
    if(res){
        cout << "Plata poate fi efectuata!\n\n";
        cout << "S-a salvat tranzactia:\n";
        this->t.addTransaction(i, newC, suma);
        this->t.printTranzactions(i);
        i++;
    } else cout << "Nu se poate efectua plata!\n";
}

void UI::runMenu() {
    int optiune;
    int i = 0;
    while(true){
        printMenu();
        cout << "Introduceti optiune:\n";
        cin >> optiune;
        if(optiune == 1){
            retragere(i);
        }
        else if(optiune == 0){
            break;
        }
    }
}

UI::UI() {
    this->c = Collection();
    /*Adaugati mai jos elemente in Colectia c folosind metoda .add(TElem elem)*/
    c.add(200);
    c.add(50);
    c.add(1);
    c.add(1);
    c.add(200);
    c.add(100);
    c.add(100);
    c.add(5);
    this->t= Repo();
    this->a = ATM(c, t);
}

