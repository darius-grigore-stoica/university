#include <cassert>
#include "Test.h"
#include "Coada.h"

void Test::TestAll() {
    testAdd();
    testRemove();
    testSize();
    testIsEmpty();
}

void Test::testAdd() {
    Coada c = Coada();
    assert(c.size() == 0);
    c.add(5);
    c.add(1);
    assert(c.size() == 2);
    c.add(100);
    assert(c.size() == 3);
}

void Test::testRemove() {
    Coada c = Coada();
    c.add(10);
    c.add(5);
    c.add(1);
    assert(c.remove() == 10);
    assert(c.remove() == 5);
}

void Test::testIsEmpty() {
    Coada c = Coada();
    assert(c.isEmpty() == true);
    c.add(10);
    c.add(5);
    c.add(1);
    assert(c.isEmpty() == false);
}

void Test::testSize() {
    Coada c = Coada();
    assert(c.size() == 0);
    c.add(10);
    assert(c.size() == 1);
    c.add(5);
    c.add(1);
    assert(c.size() == 3);
}
