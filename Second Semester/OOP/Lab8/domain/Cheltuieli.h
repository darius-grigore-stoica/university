//
// Created by Darius on 4/12/2023.
//
#include <iostream>

using namespace std;
#ifndef LAB7_CHELTUIELI_H
#define LAB7_CHELTUIELI_H

class Cheltuieli {
private:
    int nrApartament;
    int suma;
    char *tipul;
    const int CAP = 50;
public:
    Cheltuieli();

    Cheltuieli(int nrApartament, int suma, const char *tipul);

    Cheltuieli(const Cheltuieli &c);

    ~Cheltuieli();

    int getNrApartament();

    int getSuma();

    char *getTipul();

    void setNrApartament(int nrApartament);

    void setSuma(int suma);

    void setTipul(const char *tipul);

    Cheltuieli &operator=(const Cheltuieli &c);

    bool operator==(const Cheltuieli &c);

    bool operator>(const Cheltuieli &c);

    friend ostream &operator<<(ostream &os, Cheltuieli &c);

    friend istream &operator>>(istream &is, Cheltuieli &c);

};

#endif //LAB7_CHELTUIELI_H
