//
// Created by Darius Stoica on 3/2/2023.
//
#include <iostream>
using namespace std;

int n;
int* v = new int[100];

void readVector(){
    cout << "n =";
    cin >> n;
    int i;
    cout << "Array:";
    for(i = 0; i < n; i++)
        cin >> v[i];
    cout << "Read\n";
}

void printVector(){
    int i;
    for(i = 0; i < n; i++)
        cout << v[i] << " ";
    delete []v;
    cout << endl;
    cout << "Printed\n";
}
