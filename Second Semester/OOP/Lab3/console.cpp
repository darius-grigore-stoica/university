//
// Created by Darius Stoica on 3/2/2023.
//
#include <cstring>
#include <iostream>
#include "utils.h"
using namespace std;

void printMenu(){
    char menu[256];
    strcpy(menu, "1. Citirea unei liste de numere intregi\n");
    strcat(menu, "2. Afisarea unei liste de numere intregi\n");
    strcat(menu, "3. Afisarea celei mai lungi secventa cu numere distincte\n");
    strcat(menu, "4. Afisarea celei mai lungi secventa in care suma elementelor este egală cu o constantă dată \n");
    strcat(menu, "0. Iesire din aplicatie\n");
    cout << menu;
}

void Console(){
    int n = 10;
    int* v = new int[50];//dinamic allocation
    int option;
    int run = true;
    while(run) {
        printMenu();
        cout << "Introdu optiunea:";
        cin >> option;
        if (option == 1)
            readVector(n, v);
        else if (option == 2)
            printVector(n, v);
        else if (option == 0)
            run = false;
        else if (option == 3){
            int left = 0, right = 0;
            getSequence(n, v, left, right);
            int p;
            for(p = left; p < right; p++)
                cout << v[p] << " ";
            cout << "\n";
        }
        else if (option == 4) {
            int left = 0, right = 0, givenConst = 0;
            cout << "Citeste constanta data:";
            cin >> givenConst;
            getConst(n, v, left, right, givenConst);
            int p;
            for(p = left; p <= right; p++)
                cout << v[p] << " ";
            cout << "\n";
        }
        else cout << "Comanda gresita! Incearca din nou.\n";
    }
    delete[] v;
}