//
// Created by Darius on 4/12/2023.
//
#include "Student.h"
#include <string.h>
#ifndef SEM4_STUDENT_BURSIER_H
#define SEM4_STUDENT_BURSIER_H
class Student_Bursier: Student{
private:
    char* IBAN;
public:
    Student_Bursier(){
        this->IBAN = new char[256];
    };
    Student_Bursier(char* name, int age, char* IBAN){
        strcpy(this->name, name);
        strcpy(this->IBAN, IBAN);
        this->age = age;
    };
    Student_Bursier(const Student_Bursier& s){
        strcpy(this->name, s.name);
        strcpy(this->IBAN, s.IBAN);
        this->age = s.age;
    };
    char* getIBAN(){
        return this->IBAN;
    };
    void setIBAN(char* newIBAN){
        strcpy(this->IBAN, newIBAN);
    }
};
#endif //SEM4_STUDENT_BURSIER_H
