//
// Created by Darius Stoica on 3/1/2023.
//
#include <iostream>
#include "problema.h"
#include "test.h"
#include "bonus.h"
using namespace std;

int main(){
    test();
    int n;
    cin >> n;
    cout << findMinumimDigit(n) << endl;
    cout << RusseSum(5, 2);
    return 0;
}