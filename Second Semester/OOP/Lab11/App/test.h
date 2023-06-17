//
// Created by Darius on 5/3/2023.
//
#include "entitate.h"
#include "repository.h"
#include "service.h"
#include "assert.h"
#include <string.h>
#ifndef LAB9_TEST_H
#define LAB9_TEST_H
class Test {
public:
    void testOperator();
    void testGetter();
    void testSetter();

    void testAdd();
    void testRemove();
    void testUpdate();
    void testGetAt();
    void testBuy();


    void testAddService();
    void testRemoveService();
    void testUpdateService();
    void testGetAtService();

    void testRead();
    void testAll();

};
#endif //LAB9_TEST_H
