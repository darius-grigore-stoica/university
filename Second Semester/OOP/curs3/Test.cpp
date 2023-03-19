//
// Created by Darius Stoica on 3/18/2023.
//
#include "Student.h"
#include <cassert>
#include <cstring>
using namespace std;

void testGetters() {
    Student student1("Andrei", 15);
    assert(student1.getPIN() == 0);
    assert(strcmp(student1.getName(), "Andrei") == 0);
    assert(student1.getAge() == 15);
}

void testSetters(){
    Student student;
    cout << student.getName() << "5\n";

    student.setPIN(0);
    assert(student.getPIN() == 0);

    student.setName("Mihai");
    cout << student.getName() << "\n";
    //assert(strcmp(student.getName(), "Mihai") == 0);

    student.setAge(15);
    assert(student.getAge() == 15);
}

void testOperators(){
    Student student1("Andrei", 15);
    Student student2("Mihai", 15);
    student1 = student2;

    assert(student1.getPIN() == 1);
    assert(strcmp(student1.getName(), "Mihai") == 0);
    assert(student1.getAge() == 15);
}

void testAll(){
    testGetters();
    testSetters();
    testOperators();
}