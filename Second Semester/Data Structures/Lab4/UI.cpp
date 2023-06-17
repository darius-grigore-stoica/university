//
// Created by Darius on 3/31/2023.
//
#include "UI.h"

void UI::printMenu() {
    cout << "1. Retragere\n";
    cout << "2. Afiseaza tranzactii\n";
    cout << "0. Iesire\n";
}

void UI::retragere(int &i) {
    int suma;
    cout << "Suma:\n";
    do {
        cin >> suma;
    } while (suma <= 0);
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

void UI::runMenu() {
    int optiune;
    int i = 0;
    while (true) {
        printMenu();
        cout << "Introduceti optiune:\n";
        cin >> optiune;
        if (optiune == 1) {
            retragere(i);
            i++;
        } else if (optiune == 2) {
            printTranzacti();
        } else if (optiune == 0) {
            break;
        }
    }
}

UI::UI() {
    this->c = Collection();
    load("C:/Users/Darius/CLionProjects/DS/Lab4/input.txt");
    this->t = Repo();
    this->a = ATM(c, t);
}

void UI::printTranzacti() {
    int i;
    for (i = 0; i < t.getNrTrazanctii(); i++)
        cout << t.getAt(i) << endl;
}

void UI::load(string filename) {
    ifstream fin(filename);
    if (fin.is_open()) {
        string line;
        while (getline(fin, line)) {
            stringstream ss(line);
            string suma_str;
            getline(ss, suma_str);
            int bancnote = stoi(suma_str);
            this->c.add(bancnote);
        }
    } else cout << "Nu se poate deschide\n";
}

UI::~UI() = default;

