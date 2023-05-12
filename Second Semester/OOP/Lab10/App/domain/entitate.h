//
// Created by Darius on 5/3/2023.
//
#include "iostream"
using namespace std;
#ifndef LAB9_ENTITATE_H
#define LAB9_ENTITATE_H
class Produs{
protected:
    float pret;
    char* cod;
    char* nume;
public:
    Produs();
    Produs(int pret, char* cod, char* nume);
    Produs(const Produs& p);
    ~Produs();
    float getPret();
    char* getCod();
    char* getNume();
    void setPret(int pret);
    void setCod(char* cod);
    void setNume(char* nume);
    Produs& operator=(const Produs& p);
    bool operator==(const Produs& p);
    friend ostream &operator<<(ostream& os, const Produs& p);
};
#endif //LAB9_ENTITATE_H
