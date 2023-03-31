//
// Created by Darius on 3/22/2023.
//

#include "Square.h"
#include "Repo.h"
#include "test.h"
#include <assert.h>

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

void testAddSquare(){
    Repo repo = Repo();
    repo.addSquare(5, 10);
    repo.addSquare(100, 50);
    Square s = Square(5, 10);
    Square s2 = Square(100, 50);
    assert(repo.getAt(0) == s);
    assert(repo.getAt(1) == s2);
}

void testResize(){
    Repo repo = Repo();
    repo.resize();
    assert(repo.getCapacity() == 20);
}

void testRepoGetters(){
    Repo repo = Repo();
    repo.addSquare(5, 10);
    repo.addSquare(100, 50);
    assert(repo.getCapacity() == 10);
    assert(repo.getNrElems() == 2);
}