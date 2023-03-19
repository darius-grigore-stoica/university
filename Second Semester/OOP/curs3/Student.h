//
// Created by Darius Stoica on 3/18/2023.
//

#ifndef CURS3_STUDENT_H
#define CURS3_STUDENT_H
#endif //CURS3_STUDENT_H
#include <iostream>
using namespace std;

class Student {
private:
    int PIN;
    char* name;
    int age;
public:
    Student();
    Student(const char* n, int age);
    Student(const Student& s);
    ~Student();
    int getPIN();
    char* getName();
    int getAge();
    void setPIN(int newPIN);
    void setName(char* newName);
    void setAge(int newAge);
    Student& operator=(const Student& s);
};