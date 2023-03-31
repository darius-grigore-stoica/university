//
// Created by Darius on 3/22/2023.
//

#include "Square.h"
#include "test.h"
#include <assert.h>

void testGetters(){
    Square s0 = Square();
    assert(s0.getLatura() == 0);

    Square s1 = Square(5);
    assert(s1.getLatura() == 5);

    Square s2 = Square(s1);
    assert(s1.getLatura() == s2.getLatura());
}

void testSetters(){
    Square s0 = Square();
    s0.setLatura(10);
    assert(s0.getLatura() == 10);

    Square s1 = Square(5);
    s1.setLatura(9);
    assert(s1.getLatura() == 9);

    Square s2 = Square(s1);
    s2.setLatura(100);
    assert(s2.getLatura() == 100);
}

void testArea(){
    Square s3 = Square(5);
    assert(s3.getArea() == 25);
    s3.setLatura(10);
    assert(s3.getArea() == 100);
}

void testPerimeter(){
    Square s4 = Square(5);
    assert(s4.getPerimeter() == 20);
    s4.setLatura(10);
    assert(s4.getPerimeter() == 40);
}