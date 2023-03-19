//
// Created by Darius Stoica on 3/15/2023.
//
#include "assert.h"
#include "test.h"
#include "utils.h"
#include <iostream>
using namespace std;

void testGetSequence(){
    int n = 5;
    int* v = new int[50] {1, 2, 2, 3, 4};
    int left, right;
    left = 0; right = 0;
    getSequence(n, v, left, right);
    assert(left == 2 && right == 5);
    delete[] v;
    left = 0; right = 0;

    n = 10;
    int* v1 = new int[50] {1, 2, 2, 2, 4, 4, 5 , 5, 6, 6, 7};
    getSequence(n, v1, left, right);
    assert(left == 7 && right == 9);
    delete[] v1;
    left = 0; right = 0;
}

void testGetConst(){
    int n = 10; int left, right;
    int* v = new int[50] {1, 0, 3, 4, 2, 100, 100, 4, 7, 5};
    left = 0; right = 0;
    getConst(n, v, left, right, 10);
    assert(left == 0 && right == 4);
    left = 0; right = 0;
    getConst(n, v, left, right, 200);
    assert(left == 5 && right == 6);
    left = 0; right = 0;
    getConst(n, v, left, right, 2000);
    assert(left == 0 && right == 0);
    delete[] v;
}