//
// Created by Darius on 4/17/2023.
//
#include "UI.h"
#include <iostream>
#include <string.h>
using namespace std;

UI::UI() {
    this->s = Service();
    char *gaz = new char[this->CAP];
    strcpy(gaz, "gaz");
    char *gunoi = new char[this->CAP];
    strcpy(gunoi, "gunoi");
    char *electricitate = new char[this->CAP];
    strcpy(electricitate, "electricitate");
    char *apa = new char[this->CAP];
    strcpy(apa, "apa");
    this->s.add(1, 100, gaz);
    this->s.add(1, 50, gunoi);
    this->s.add(1, 400, gaz);
    this->s.add(1, 80, gaz);
    this->u = Service(s);
}

void UI::printMenu() {
    cout << "1. Adaugarea unei cheltuieli in lista\n";
    cout << "2. Modificarea cheltuielilor din lista\n";
    cout << "3. Listarea cheltuielilor din lista\n";
    cout << "4. Obtinerea unor proprietati ale cheltuielilor\n";
    cout << "5. Filtrari\n";
    cout << "6. Undo\n";
    cout << "0. Iesire\n";
}

void UI::runMenu() {
    int option;
    while (true) {
        printMenu();
        cout << "Introduceti optiunea: \n";
        cin >> option;
        if (option == 1) {
            add();
        } else if (option == 2) {
            update();
        } else if (option == 3) {
            print();
        } else if (option == 4) {
            stats();
        } else if (option == 5) {
            filter();
        } else if (option == 6) {
            undo();
        } else if (option == 0) {
            break;
        } else {
            cout << "Comanda gresita!";
        }
    }
}

void UI::add() {
    Cheltuieli c;
    cin >> c;
    this->u = s;
    this->s.add(c.getNrApartament(), c.getSuma(), c.getTipul());
}

void UI::update() {
    int option;
    cout << "Alege modul de modificare:\n";
    cout << "1. Eliminare\n";
    cout << "2. Actualizare\n";
    cin >> option;
    if (option == 1) {
        int pos;
        cout << "Nr. de ordine a cheltuielii pentru stergere:\n";
        cin >> pos;
        this->u = s;
        this->s.remove(pos);
        cout << "Cheltuiala de pe pozitia " << pos << " a fost stearsa!\n";
    } else if (option == 2) {
        int pos;
        cout << "Nr. de ordine a cheltuielii pentru actualizare:\n";
        cin >> pos;
        Cheltuieli c;
        cin >> c;
        this->u = s;
        this->s.update(pos, c.getNrApartament(), c.getSuma(), c.getTipul());
    }
}

void UI::print() {
    int option;
    cout << "Alege modul de listare:\n";
    cout << "1. Listarea tuturor cheltuielilor\n";
    cout << "2. Listarea cheltuielilor mai mari decat un intreg\n";
    cout << "3. Listarea cheltuielilor mai mici decat un intreg\n";
    cout << "4. Listarea cheltuielilor egale cu un intreg\n";
    cin >> option;
    int i;
    if (option == 1) {
        cout << this->s;
    } else if (option == 2) {
        int larger;
        cout << "Introduceti intregul:\n";
        cin >> larger;
        for (i = 0; i < this->s.getSize(); i++) {
            Cheltuieli c = this->s.getAt(i);
            if (c.getSuma() > larger)
                cout << c;
        }
    } else if (option == 3) {
        int smaller;
        cout << "Introduceti intregul:\n";
        cin >> smaller;
        for (i = 0; i < this->s.getSize(); i++) {
            Cheltuieli c = this->s.getAt(i);
            if (c.getSuma() < smaller)
                cout << c;
        }
    } else if (option == 4) {
        int equal;
        cout << "Introduceti intregul:\n";
        cin >> equal;
        for (i = 0; i < this->s.getSize(); i++) {
            Cheltuieli c = this->s.getAt(i);
            if (c.getSuma() == equal)
                cout << c;
        }
    }
}

void UI::stats() {
    cout << "Alegeti caracteristice ce reprezinta interes:\n";
    cout << "1. Suma tuturor cheltuielilor cu un anumit tip\n";
    cout << "2. Cea mai costisitoare cheltuiala a unui apartament\n";
    cout << "3. Ordonarea descrescatoare a cheltuielilor de un anumit tip\n";
    int option;
    cin >> option;
    if (option == 1) {
        char *atr = new char[this->CAP];
        cout << "Tipul dorit:\n";
        cin.ignore();
        cin.getline(atr, this->CAP);
        cout << "Suma tuturor cheltuielilor cu tipul " << atr << " este : " << this->s.sum(atr) << endl;
    }
    else if (option == 2) {
        int nrApartament;
        cout << "Numarul apartamentului:\n";
        cin >> nrApartament;
        Cheltuieli c;
        c = this->s.max(nrApartament);
        cout << "Cea mai costisitoare cheltuiala a apartamentului " << nrApartament << " este:\n" << c;
    }
    else if (option == 3) {
        char *atr = new char[this->CAP];
        cout << "Tipul dorit:\n";
        cin.ignore();
        cin.getline(atr, this->CAP);
        Repo rep;
        rep = this->s.sort(atr);
        int size = rep.getSize();
        int i;
        for (i = 0; i < size; i++) {
            Cheltuieli c = rep.getAt(i);
            cout << c << endl;
        }
    }
}

void UI::filter() {
    int option;
    cout << "Alegeti metoda de filtrare:\n";
    cout << "1. Filtrarea tuturor cheltuielilor cu un anumit tip\n";
    cout << "2. Filtrarea tuturor cheltuielilor cu suma mai mica decat un intreg\n";
    cin >> option;
    char *atr_c = new char[this->CAP];
    int atr_i;
    Service ser = Service();
    if (option == 1) {
        cout << "Tipul dorit:\n";
        cin.ignore();
        cin.getline(atr_c, this->CAP);
        ser = this->s.filterChar(atr_c);
    } else if (option == 2) {
        cout << "Intreg:\n";
        cin >> atr_i;
        ser = this->s.filterInteger(atr_i);
    }
    this->u = this->s;
    this->s = ser;
    cout << ser;
}

void UI::undo() {
    this->s = this->u;
    cout << "Undo efectual!\n";
}
