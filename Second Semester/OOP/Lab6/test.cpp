//
// Created by Darius on 3/22/2023.
//

#include "Square.h"
#include "Repo.h"
#include "test.h"
#include <assert.h>

void testAll(){
    testGetters();
    testSetters();
    testArea();
    testPerimeter();
    testIsInFirst();
    testAddSquare();
    testRepoGetters();
    testGetMaxim();
    testGetSequenceOfEquals();
}

void testGetters(){
    Square s0 = Square();
    assert(s0.getLatura() == 0);

    Square s1 = Square(5, 3);
    assert(s1.getLatura() == 2);

    Square s2 = Square(s1);
    assert(s1.getLatura() == s2.getLatura());
}

void testSetters(){
    Square s0 = Square();
    s0.setLatura(2, 5);
    assert(s0.getLatura() == 3);

    Square s1 = Square(5, 6);
    s1.setLatura(9, 4);
    assert(s1.getLatura() == 5);

    Square s2 = Square(s1);
    s2.setLatura(100, 1);
    assert(s2.getLatura() == 99);
}

void testArea(){
    Square s3 = Square(5, 4);
    assert(s3.getArea() == 1);
    s3.setLatura(10, 5);
    assert(s3.getArea() == 25);
}

void testPerimeter(){
    Square s4 = Square(5, 2);
    assert(s4.getPerimeter() == 12);
    s4.setLatura(10, 8);
    assert(s4.getPerimeter() == 8);
}

void testIsInFirst(){
    Square s1 = Square(5, 10);
    Square s2 = Square(-3, 7);
    Square s3 = Square(5, 100);
    assert(s1.isInFirst() == true);
    assert(s2.isInFirst() == false);
    assert(s3.isInFirst() == false);
}

void testAddSquare(){
    Repo repo = Repo();
    repo.addSquare(5, 10);
    repo.addSquare(100, 50);
    Square s = Square(5, 10);
    Square s2 = Square(100, 50);
    assert(repo.getAt(0) == s);
    assert(repo.getAt(1) == s2);
}

void testRepoGetters(){
    Repo repo = Repo();
    repo.addSquare(5, 10);
    repo.addSquare(100, 50);
    assert(repo.getCapacity() == 10);
    assert(repo.getNrElems() == 2);
}

void testGetMaxim(){
    //test for positive values
    Repo repo = Repo();
    repo.addSquare(5, 10);
    repo.addSquare(100, 50);
    Square max = Square(100, 50);
    assert(repo.getMaxim() == max);

    //test for negative values
    Repo repoN = Repo();
    repoN.addSquare(5, 5);
    repoN.addSquare(-7, 6);
    Square maxN = Square(-7, 6);
    assert(repoN.getMaxim() == maxN);
}

void testGetSequenceOfEquals(){
    Repo r = Repo();
    int left; int right;
    left = 0; right = 0;
    r.getSequenceOfEquals(left, right);
    assert(left == 0 && right == 0);
    r.addSquare(4, 6);
    r.addSquare(4, 8);
    r.addSquare(4, 8);
    r.addSquare(1, 90);
    r.getSequenceOfEquals(left, right);
    assert(left == 1 && right == 2);

    r.addSquare(99, 100);
    r.addSquare(99, 100);
    r.addSquare(4, 6);
    left = 0, right = 0;
    r.getSequenceOfEquals(left, right);
    assert(left == 3 && right == 4);
}