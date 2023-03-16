//
// Created by Darius Stoica on 3/15/2023.
//
#include "assert.h"
#include "test.h"
#include "utils.h"
#include <iostream>
using namespace std;

void test_getInterval(){
    int n = 6;
    int v[] = {1, 4, 5, -6, 6, 7};
    int left, right;
    getInterval(0, 5, n, v, left, right);
    assert(left == 0 && right == 3);
}

void test_getSign(){
    int n = 6, v[] = {1, -4, 5, -6, 6, 7};
    int left = 0, right = 0;
    getSign(n, v, left, right);
    assert(left == 0 && right == 3);
}
