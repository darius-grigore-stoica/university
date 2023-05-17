//
// Created by Darius on 3/31/2023.
//
#include "UI.h"

void UI::printMenu() {
    cout << "1. Retragere\n";
    cout << "2. Afiseaza tranzactii\n";
    cout << "0. Iesire\n";
}

void UI::retragere(int& i){
    ifstream fin("C:/Users/Darius/CLionProjects/DS/Lab4/input.txt");
    if(fin.is_open()){
        string line;
        while(getline(fin, line)){
            stringstream ss(line);
            string suma_str;
            getline(ss, suma_str);
            int suma = stoi(suma_str);
            Collection newC;
            this->c.sort();
            bool res;
            int index = 0;
            res = this->a.backtracking(this->c, suma, newC, index);
            if (res) {
                Tranzactie trans = Tranzactie(newC, suma);
                this->t.add(trans);
            } else cout << "Nu se poate efectua plata pentru suma " << suma << endl;
        }
    } else throw invalid_argument("fisierul nu poate fi deschis");
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
            i++;
        }
        else if(optiune== 2){
            printTranzacti();
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
    c.add(10);
    c.add(10);
    c.add(5);
    this->t= Repo();
    this->a = ATM(c, t);
}

void UI::printTranzacti() {
    int i;
    for(i = 0; i < t.getNrTrazanctii(); i++)
        cout << t.getAt(i) << endl;
}

UI::~UI()= default;

