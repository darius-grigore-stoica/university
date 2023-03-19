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
    strcat(menu, "3. Afisarea celei mai lungi secventa cu elemente cuprinse in intervalul [a, b]\n");
    strcat(menu, "4. Afisarea celei mai lungi secventa cu elemente care au semn diferit 2 cate 2\n");
    strcat(menu, "0. Iesire din aplicatie\n");
    cout << menu;
}

void Console(){
    int n = 6;
    int v[50];
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
            cout << "Citeste a si b:\n";
            int a, b;
            cin >> a >> b;
            getInterval(a, b, n, v, left, right);
            int p;
            for(p = left; p < right; p++)
                cout << v[p] << " ";
            cout << "\n";
        }
        else if (option == 4) {
            int left = 0, right = 0;
            getSign(n, v, left, right);
            int p;
            for(p = left; p <= right; p++)
                cout << v[p] << " ";
            cout << "\n";
        }
        else cout << "Comanda gresita! Incearca din nou.\n";
    }
}