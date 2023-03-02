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
    strcat(menu, "0. Iesire din aplicatie\n");

    cout << menu;
}

void Console(){
    int option;
    int run = true;
    while(run) {
        printMenu();
        cin >> option;
        if (option == 1)
            readVector();
        else if (option == 2)
            printVector();
        else if (option == 0)
            run = false;
        else cout << "Comanda gresita! Incearca din nou.\n";
    }
}