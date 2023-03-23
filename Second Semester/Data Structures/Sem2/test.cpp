//
// Created by Darius on 3/23/2023.
//

#include "test.h"
#include "cassert"
#include "Colection.h"

void testAll(){
    testAdd();
    testSize();
    testSearch();
    testRemove();
    testNrOccurences();
    testGetAt();
}

void testAdd(){
    Collection c = Collection();
    c.add(5);
    c.add(10);
    c.add(-4);
    c.add(-100);
    assert(c.getAt(0) == 5);
    assert(c.getAt(2) == -4);
    assert(c.getAt(100) == -1);
}

void testSize(){
    Collection c = Collection();
    c.add(5);
    c.add(5);
    c.add(8);
    c.add(-5);
    c.add(100);
    assert(c.size() == 5);
    c.add(10);
    c.add(100);
    c.add(4);
    c.add(-4);
    c.add(10);
    assert(c.size() == 10);
}

void testRemove(){
    Collection c = Collection();
    c.add(5);
    c.add(8);
    c.add(-5);
    c.add(100);
    c.remove(5);
    assert(c.search(5) == false && c.size() == 4);
    c.remove(100);
    assert(c.search(100) == false && c.size() == 3);
}

void testSearch(){
    Collection c = Collection();
    c.add(5);
    c.add(5);
    c.add(8);
    c.add(-5);
    c.add(100);
    assert(c.search(5) == true);
    assert(c.search(18) == false);
    assert(c.search(-100) == false);
    assert(c.search(1) == false);
    assert(c.search(-5) == true);
}

void testNrOccurences(){
    Collection c = Collection();
    c.add(5);
    c.add(5);
    c.add(8);
    c.add(-5);
    c.add(100);
    assert(c.nroccurrences(5) == 2);
    assert(c.nroccurrences(-5) == 1);
    assert(c.nroccurrences(109) == 0);
    assert(c.nroccurrences(17) == 0);
}

void testGetAt(){
    Collection c = Collection();
    c.add(5);
    c.add(5);
    c.add(8);
    c.add(-5);
    c.add(100);
    assert(c.getAt(2) == 8);
    assert(c.getAt(5) == -1);
    assert(c.getAt(4) == 100);
}