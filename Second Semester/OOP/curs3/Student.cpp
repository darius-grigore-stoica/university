//
// Created by Darius Stoica on 3/18/2023.
//
#include "Student.h"
#include <iostream>
#include <cstring>
using namespace std;

int uniquePIN = 0;
Student::Student() {
    uniquePIN++;
    this->PIN = uniquePIN;
    this->name = NULL;
    this->age = 0;
}

Student::Student(const char *n, int age) {
    this->PIN = uniquePIN;
    this->name = new char[strlen(n) + 1];
    strcpy_s(this->name, strlen(n) + 1, n);
    this->age = age;
}

Student::Student(const Student &s) {
    this->PIN = s.PIN;
    this->name = new char[strlen(s.name) + 1];
    strcpy_s(this->name, strlen(s.name) + 1, s.name);
    this->age = s.age;
}

Student::~Student() {
    if(this->name != NULL){
        delete[] this->name;
        this->name = NULL;
    }
    cout << "Deleted...\n";
}

int Student::getPIN() {
    return this->PIN;
}

char* Student::getName() {
    return this->name;
}

int Student::getAge() {
    return this->age;
}

void Student::setPIN(int newPIN) {
    this->PIN = newPIN;
}

void Student::setName(char* newName) {
    if(this->name != NULL){
        delete[] this->name;
    }
    this->name = new char[strlen(newName) + 1];
    strcpy_s(this->name, strlen(newName + 1), newName);
}

void Student::setAge(int newAge) {
    this->age = newAge;
}

Student& Student::operator=(const Student &s) {
    this->setPIN(s.PIN);
    this->setName(s.name);
    this->setAge(s.age);
    return *this;
}