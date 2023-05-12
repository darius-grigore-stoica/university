//
// Created by Darius on 5/3/2023.
//
#include "test.h"

void Test::testOperator() {
    Produs p = Produs();
    char *cod = new char[256];
    char *nume = new char[256];
    strcpy(cod, "05c9");
    strcpy(nume, "Kinder");
    Produs p1 = Produs(10, cod, nume);
    p = p1;
    assert(p.getPret() == 10);
    assert(strcmp(p.getCod(), cod) == 0);
    assert(strcmp(p.getNume(), nume) == 0);
}

void Test::testGetter() {
    Produs p = Produs();
    assert(p.getPret() == -1);
    char *cod = new char[256];
    char *nume = new char[256];
    strcpy(cod, "05c9");
    strcpy(nume, "Kinder");
    Produs p1 = Produs(10, cod, nume);
    assert(strcmp(p1.getCod(), cod) == 0);
    assert(strcmp(p1.getNume(), nume) == 0);

}

void Test::testSetter() {
    char *cod = new char[256];
    char *nume = new char[256];
    strcpy(cod, "05c9");
    strcpy(nume, "Kinder");
    Produs p = Produs();
    p.setCod(cod);
    p.setNume(nume);
    p.setPret(10);
    assert(p.getPret() == 10);
    assert(strcmp(p.getCod(), cod) == 0);
    assert(strcmp(p.getNume(), nume) == 0);

}

void Test::testAdd() {
    char *cod = new char[256];
    char *nume = new char[256];
    strcpy(cod, "05c9");
    strcpy(nume, "Kinder");
    Produs p = Produs(10, cod, nume);
    Repo r = Repo();
    r.add(p);
    assert(r.size() == 1);
}

void Test::testRemove() {
    char *cod = new char[256];
    char *nume = new char[256];
    strcpy(cod, "05c9");
    strcpy(nume, "Kinder");
    Produs p = Produs(10, cod, nume);
    Repo r = Repo();
    r.add(p);
    assert(r.size() == 1);
    r.remove(0);
    assert(r.size() == 0);
}

void Test::testUpdate() {
    char *cod = new char[256];
    char *nume = new char[256];
    strcpy(cod, "05c9");
    strcpy(nume, "Kinder");

    Produs p = Produs(10, cod, nume);
    Repo r = Repo();
    r.add(p);
    Produs p1 = Produs(5, cod, nume);
    r.update(p1, 0);
    assert(r.getAt(0) == p1);

}

void Test::testGetAt() {
    char *cod = new char[256];
    char *nume = new char[256];
    strcpy(cod, "05c9");
    strcpy(nume, "Kinder");

    Produs p = Produs(10, cod, nume);
    Repo r = Repo();
    r.add(p);
    r.add(p);
    r.add(p);
    Produs p1 = Produs(5, cod, nume);
    r.update(p1, 0);
    assert(r.getAt(0) == p1);
    assert(r.getAt(1) == p);
}

void Test::testAll() {
    testGetter();
    testSetter();
    testOperator();
    testAdd();
    testRemove();
    testUpdate();
    testGetAt();
    testAddService();
    testRemoveService();
    testUpdateService();
    testGetAtService();
    testBuy();
}

void Test::testAddService() {
    Produs p = Produs(10, "05c9", "Kinder");
    Service s = Service();
    assert(s.size() == 0);
    s.add(p.getPret(), p.getCod(), p.getNume());
    assert(s.size() == 1);
}

void Test::testRemoveService() {
    Produs p = Produs(10, "05c9", "Kinder");
    Produs p1 = Produs(15, "0x56g", "Milka");
    Service s = Service();
    s.add(p.getPret(), p.getCod(), p.getNume());
    s.add(p1.getPret(), p1.getCod(), p1.getNume());
    s.remove(0);
    assert(s.getAt(0) == p1);
}

void Test::testUpdateService() {
    Produs p = Produs(10, "05c9", "Kinder");
    Produs p1 = Produs(15, "0x56g", "Milka");
    Produs p2 = Produs(20, "1x56", "Snikers");
    Service s = Service();
    s.add(p.getPret(), p.getCod(), p.getNume());
    s.add(p1.getPret(), p1.getCod(), p1.getNume());

    s.update(p2.getPret(), p2.getCod(), p2.getNume(), 1);
    assert(s.getAt(1) == p2);
}

void Test::testGetAtService() {
    Produs p = Produs(10, "05c9", "Kinder");
    Service s = Service();
    s.add(p.getPret(), p.getCod(), p.getNume());
    assert(s.getAt(0) == p);
}

void Test::testBuy() {
    Service s = Service();
    Produs p1 = Produs(10, "2xgh", "Apa");
    s.add(p1.getPret(), p1.getCod(), p1.getNume());
    assert(s.buy("Apa", 15) == 5);
}
