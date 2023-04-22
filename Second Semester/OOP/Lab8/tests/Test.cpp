//
// Created by Darius on 4/12/2023.
//
#include "Test.h"
#include "../domain/Cheltuieli.h"
#include "../repository/Repo.h"
#include "../service/Service.h"
#include <string.h>
#include "assert.h"

void Test::testGetNrApartament() {
    Cheltuieli c1 = Cheltuieli();
    assert(c1.getNrApartament() == -1);
    Cheltuieli c2 = Cheltuieli(5, 100, "Apa");
    assert(c2.getNrApartament() == 5);
    Cheltuieli c3 = Cheltuieli(c2);
    assert(c3.getNrApartament() == 5);
}

void Test::testGetSuma() {
    Cheltuieli c1 = Cheltuieli();
    assert(c1.getSuma() == -1);
    Cheltuieli c2 = Cheltuieli(5, 100, "Apa");
    assert(c2.getSuma() == 100);
    Cheltuieli c3 = Cheltuieli(c2);
    assert(c3.getSuma() == 100);
}

void Test::testGetTipul() {
    Cheltuieli c1 = Cheltuieli(5, 100, "Apa");
    assert(strcmp(c1.getTipul(), "Apa") == 0);
    Cheltuieli c2 = Cheltuieli(c1);
    assert(strcmp(c2.getTipul(), "Apa") == 0);

}

void Test::testSetNrApartament() {
    Cheltuieli c1 = Cheltuieli();
    assert(c1.getNrApartament() == -1);
    c1.setNrApartament(10);
    assert(c1.getNrApartament() == 10);
}

void Test::testSetSuma() {
    Cheltuieli c = Cheltuieli(5, 234, "Apa");
    c.setSuma(0);
    assert(c.getSuma() == 0);
}

void Test::testSetTipul() {
    Cheltuieli c = Cheltuieli(5, 234, "Apa");
    c.setTipul("gaz");
    assert(strcmp(c.getTipul(), "gaz") == 0);
}

void Test::testCheltuieli() {
    testGetNrApartament();
    testGetSuma();
    testGetTipul();
    testSetSuma();
    testSetTipul();
    testSetNrApartament();
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

void Test::testUpdate() {
    Cheltuieli c1 = Cheltuieli(5, 100, "Apa");
    Cheltuieli c2 = Cheltuieli(5, 500, "Gaz");
    Repo r = Repo();
    r.add(c1);
    r.add(c2);
    r.update(0, c2);
    assert(r.getAt(0) == c2);
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

void Test::testGetAt() {
    Cheltuieli e = Cheltuieli(5, 100, "Apa");

    Repo r = Repo();
    r.add(e);

    assert(e == r.getAt(0));
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

void Test::testSwap() {
    Cheltuieli c1 = Cheltuieli(5, 100, "Apa");
    Cheltuieli c2 = Cheltuieli(5, 500, "Gaz");
    Repo r = Repo();
    r.add(c1);
    r.add(c2);
    r.swap(0, 1);
    assert(r.getAt(0) == c2);
    assert(r.getAt(1) == c1);
}

void Test::testRepo() {
    testAdd();
    testUpdate();
    testRemove();
    testGetAt();
    testGetSize();
    testSwap();
}

void Test::testAddService() {
    Service s = Service();
    assert(s.getSize() == 0);
    char curent[50] = "curent electric";
    char apa[50] = "apa curenta";
    s.add(100, 435, curent);
    s.add(108, 80, apa);
    assert(s.getSize() == 2);
}

void Test::testUpdateService() {
    Service s = Service();
    char curent[50] = "curent electric";
    char apa[50] = "apa curenta";
    s.add(100, 435, curent);
    s.add(108, 80, apa);
    s.update(0, 1, 100, apa);
    Cheltuieli cheltuiala = Cheltuieli(1, 100, apa);
    assert(s.getAt(0) == cheltuiala);
    Cheltuieli cheltuiala2 = Cheltuieli(108, 80, apa);
    assert(s.getAt(1) == cheltuiala2);
}

void Test::testRemoveService() {
    Service s = Service();
    char curent[50] = "curent electric";
    char apa[50] = "apa curenta";
    s.add(100, 435, curent);
    s.add(108, 80, apa);
    s.remove(0);

    Cheltuieli cheltuiala = Cheltuieli(108, 80, apa);
    assert(s.getAt(0) == cheltuiala);
}

void Test::testGetAtService() {
    Cheltuieli e = Cheltuieli(5, 100, "Apa");

    Repo r = Repo();
    r.add(e);

    Service s = Service(r);
    assert(e == s.getAt(0));
}

void Test::testGetSizeService() {
    Cheltuieli elem = Cheltuieli(5, 100, "Apa");
    Repo r = Repo();
    Service s = Service(r);
    assert(s.getSize() == 0);
    s.add(elem.getNrApartament(), elem.getSuma(), elem.getTipul());
    assert(s.getSize() == 1);
    s.add(elem.getNrApartament(), elem.getSuma(), elem.getTipul());
    assert(s.getSize() == 2);
}

void Test::testSwapService() {
    Cheltuieli c1 = Cheltuieli(5, 100, "Apa");
    Cheltuieli c2 = Cheltuieli(5, 500, "Gaz");
    Repo r = Repo();
    r.add(c1);
    r.add(c2);
    Service s = Service(r);
    s.swap(0, 1);
    assert(s.getAt(0) == c2);
    assert(s.getAt(1) == c1);
}

void Test::testSum() {
    Cheltuieli c1 = Cheltuieli(5, 100, "Apa");
    Cheltuieli c2 = Cheltuieli(5, 500, "Gaz");
    Cheltuieli c3 = Cheltuieli(1, 80, "Gaz");
    Repo r = Repo();
    r.add(c1);
    r.add(c2);
    r.add(c3);
    Service s = Service(r);
    assert(s.sum("Apa") == 100);
    assert(s.sum("Gaz") == 580);
}

void Test::testMax() {
    Cheltuieli c1 = Cheltuieli(5, 100, "Apa");
    Cheltuieli c2 = Cheltuieli(5, 500, "Gaz");
    Cheltuieli c3 = Cheltuieli(1, 80, "Gaz");
    Repo r = Repo();
    r.add(c1);
    r.add(c2);
    r.add(c3);
    Service s = Service(r);
    assert(s.max(1) == c3);
    assert(s.max(5) == c2);
}

void Test::testSort() {
    Cheltuieli c1 = Cheltuieli(5, 100, "Apa");
    Cheltuieli c2 = Cheltuieli(5, 500, "Gaz");
    Cheltuieli c3 = Cheltuieli(1, 80, "Gaz");
    Repo r = Repo();
    r.add(c1);
    r.add(c2);
    r.add(c3);
    Service s = Service(r);
    Repo rep = s.sort("Gaz");
    assert(rep.getAt(0) == c2);
    assert(rep.getAt(1) == c3);
}

void Test::testFilter() {
    Cheltuieli c1 = Cheltuieli(5, 100, "Apa");
    Cheltuieli c2 = Cheltuieli(5, 500, "Gaz");
    Cheltuieli c3 = Cheltuieli(1, 80, "Gaz");
    Repo r = Repo();
    r.add(c1);
    r.add(c2);
    r.add(c3);
    Service s = Service(r);

    Service sInteger = Service();
    sInteger.add(c3.getNrApartament(), c3.getSuma(), c3.getTipul());
    assert(s.filterInteger(100).getAt(0) == sInteger.getAt(0));

    Service sChar = Service();
    sChar.add(c1.getNrApartament(), c1.getSuma(), c1.getTipul());
    assert(s.filterChar("Apa").getAt(0) == sChar.getAt(0));
}

void Test::testService() {
    testAddService();
    testUpdateService();
    testRemoveService();
    testGetAtService();
    testGetSizeService();
    testSwapService();
    testSum();
    testMax();
    testFilter();
    testSort();
}