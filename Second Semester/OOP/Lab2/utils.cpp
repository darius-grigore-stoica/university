//
// Created by Darius Stoica on 3/2/2023.
//
#include <iostream>
using namespace std;

int n = 7;
int v[] = {0, 1, 3, 2, 10, 5, 1};

void readVector(){
    cout << "n =";
    cin >> n;
    int i;
    cout << "Array:";
    for(i = 0; i < n; i++)
        cin >> v[i];
}

void printVector(){
    int i;
    for(i = 0; i < n; i++)
        cout << v[i] << " ";
    cout << "\n";
}

void getInterval(int a, int b){
    int i, j;
    pair<int, int> pozitii;
    int max;

    i = 0; max = 0;
    while (i < n && (v[i] < a || v[i] > b))
        i++;
    while(i < n - 1) {
        j = i + 1;
        while (j < n && (v[j] >= a && v[j] <= b))
            j++;
        if(j - i > max) {
            pozitii.first = i;
            pozitii.second = j;
            max = j - i;
        }
        i++;
    }

    int p;
    for(p = pozitii.first; p < pozitii.second; p++)
        cout << v[p] << " ";
    cout << "\n";
}

void getSequence(){
    int a, b;
    cout << "Ofera o valoare pentru capatul din stanga si cel din dreapta al intervalului:";
    cin >> a >> b;
    getInterval(a, b);
}

bool diffSign(int y){
    if (v[y - 1] - v[y] > 0 && v[y] - v[y + 1] < 0)
        return true;
    if (v[y - 1] - v[y] < 0 && v[y] - v[y + 1] > 0)
        return true;
    return false;
}

void getSign(){
    int i, j;
    pair<int, int> pozitii;
    int max;

    i = 0;
    while(!diffSign(i))
        i++;

    max = 0;
    pozitii.first = 0;
    pozitii.second = -1;
    while(i < n - 1){
        j = i + 1;
        while(diffSign(j))
            j++;
        if(j - i > max){
            max = j - i;
            pozitii.first = i - 1;
            pozitii.second = j;
        }
        i = j + 1;
    }

    int p;
    for(p = pozitii.first; p <= pozitii.second; p++)
        cout << v[p] << " ";
    cout << "\n";
}