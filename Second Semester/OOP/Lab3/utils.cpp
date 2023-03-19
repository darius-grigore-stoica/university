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

/*
 * In:
 * n - lenght of the v array
 * v - array of integers
 * left, right - indexes to memorize the position of start/end element of the sequence
 * Out:
 * Desc: Find the largest sequence with distinct elements
 * */
void getSequence(int &n, int v[50], int& left, int& right){
    int i, j, max;
    max = -1;
    j = 0;
    while(j < n) {
        i = j;
        while ((i < n - 1) && (v[i] != v[i + 1]))
            ++i;
        if(max <= i - j){
            right = i + 1;
            left = j;
            max = i - j;
        }
        ++j;
    }
}

/*
 * Desc: Find the sum of the elements between i-th and j-th element of the v array
 * In: v - array; i, j - integers
 * Out: the sum of the elements of the v array, from i to j position
 * */
int getSum(int v[50], int i, int j){
    int sum = 0;
    for(int p = i; p <= j; p++){
        sum += v[p];
    }
    return sum;
}

/*
 * In:
 * n - lenght of the v array
 * v - array of integers
 * left, right - indexes to memorize the position of start/end element of the sequence
 * givenConst - given constant, input from console.cpp
 * Out:
 * Desc: Find the largest sequence with the sum of the elements equal to the givenConst
 * */
void getConst(int& n, int v[50], int& left, int& right, int givenConst){
    int i, j, max;
    i = 0;
    max = 0;
    while(i < n){
        j = i + 1;
        while(givenConst - getSum(v, i, j) > 0)
            j++;
        if(givenConst - getSum(v, i, j) == 0 && max <= j - i){
            max = j - i;
            left = i;
            right = j;
        }
        i++;
    }
}