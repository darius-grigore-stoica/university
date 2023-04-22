//
// Created by Darius on 4/12/2023.
//
#include "Test.h"
#include "Repo.h"
#include "assert.h"

void Test::testCheltuieli() {
    testGetNrApartament();
    testSetNrApartament();
}

void Test::testGetNrApartament() {
    Cheltuieli c1 = Cheltuieli();
    assert(c1.getNrApartament() == -1);
    Cheltuieli c2 = Cheltuieli(5, 100, "Apa");
    assert(c2.getNrApartament() == 5);
    Cheltuieli c3 = Cheltuieli(c2);
    assert(c3.getNrApartament() == 5);
}

void Test::testSetNrApartament() {
    Cheltuieli c1 = Cheltuieli();
    assert(c1.getNrApartament() == -1);
    c1.setNrApartament(10);
    assert(c1.getNrApartament() == 10);
}

void Test::testAdd() {
    Cheltuieli elem = Cheltuieli(5, 100, "Apa");
    Repo r = Repo();
    assert(r.getSize() == 0);
    r.add(elem);
    assert(r.getSize() == 1);
    r.add(elem);
    assert(r.getSize() == 2);
}

void Test::testGetAll() {
    Cheltuieli e = Cheltuieli(5, 100, "Apa");

    Repo r = Repo();
    r.add(e);

    Cheltuieli* elems = new Cheltuieli[2];
    elems = r.getAll();
    assert(e == elems[0]);
}

void Test::testGetSize() {
    Cheltuieli elem = Cheltuieli(5, 100, "Apa");
    Repo r = Repo();
    assert(r.getSize() == 0);
    r.add(elem);
    assert(r.getSize() == 1);
    r.add(elem);
    assert(r.getSize() == 2);
}

void Test::testUpdate() {
    Cheltuieli c1 = Cheltuieli(5, 100, "Apa");
    Cheltuieli c2 = Cheltuieli(5, 500, "Gaz");
    Repo r = Repo();
    r.add(c1);
    r.add(c2);
    r.update(0, c2);
    assert(r.getAll()[0] == c2);
}

void Test::testRemove() {
    Cheltuieli c1 = Cheltuieli(5, 100, "Apa");
    Cheltuieli c2 = Cheltuieli(5, 500, "Gaz");
    Repo r = Repo();
    r.add(c1);
    r.add(c2);
    assert(r.getSize() == 2);
    r.remove(1);
    assert(r.getSize() == 1);
}

void Test::testRepo() {
    testAdd();
    testUpdate();
    testRemove();
    testGetAll();
    testGetSize();
}
