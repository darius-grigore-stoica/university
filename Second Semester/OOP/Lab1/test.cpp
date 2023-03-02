//
// Created by Darius Stoica on 3/1/2023.
//
#include <assert.h>
#include "problema.h"
#include "bonus.h"

void test(){
    assert(getNumberOfDigits(1234) == 4);
    assert(getNumberOfDigits(506) == 3);
    assert(getNumberOfDigits(10) == 2);

    assert(findMinumimDigit(7634) == 3467);
    assert(findMinumimDigit(3658) == 3568);
    assert(findMinumimDigit(123) == 123);
    assert(findMinumimDigit(1) == 1);

    assert(RusseSum(5, 2) == 10);
    assert(RusseSum(2, 2) == 4);
    assert(RusseSum(10, 2) == 20);
}