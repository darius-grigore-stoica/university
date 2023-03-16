//
// Created by Darius Stoica on 3/2/2023.
//
#include <iostream>
using namespace std;

void readVector(int& n, int v[50]){
    cout << "n =";
    cin >> n;
    int i;
    cout << "Array:";
    for(i = 0; i < n; i++)
        cin >> v[i];
}

void printVector(int& n, int v[50]){
    int i;
    for(i = 0; i < n; i++)
        cout << v[i] << " ";
    cout << "\n";
}

void getInterval(int a, int b, int& n, int v[50], int& left, int& right){
    int i, j;
    int max;
    left = 0;
    right = 0;

    i = 0; max = 0;
    while (i < n && (v[i] < a || v[i] > b))
        i++;
    while(i < n - 1) {
        j = i + 1;
        while (j < n && (v[j] >= a && v[j] <= b))
            j++;
        if(j - i > max && j < n - 1) {
            left = i;
            right = j;
            max = j - i;
        }
        i = j + 1;
    }
}

bool diffSign(int y, int v[50]){
    if (v[y - 1] - v[y] > 0 && v[y] - v[y + 1] < 0)
        return true;
    if (v[y - 1] - v[y] < 0 && v[y] - v[y + 1] > 0)
        return true;
    return false;
}

void getSign(int& n, int v[50], int& left, int& right ){
    int i, j;
    int max;
    left = 0;
    right = 0;

    i = 0;
    while(!diffSign(i, v))
        i++;

    max = 0;
    while(i < n - 1){
        j = i + 1;
        while(diffSign(j, v))
            j++;
        if(j - i > max && j < n - 1){
            max = j - i;
            left = i;
            right = j - 1;
        }
        i = j + 1;
    }
}