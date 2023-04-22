//
// Created by Darius on 4/14/2023.
//
#include "../repository/Repo.h"

#ifndef LAB7_SERVICE_H
#define LAB7_SERVICE_H

class Service {
private:
    Repo r;
public:
    Service();

    explicit Service(Repo &r);

    Service(const Service &s);

    ~Service();

    void add(int nrApartament, int suma, char *tipul);

    void update(int pos, int nrApartament, int suma, char *tipul);

    void remove(int pos);

    Cheltuieli getAt(int pos);

    int getSize();

    int sum(const char* atr);

    Cheltuieli max(int nrApartament);

    Repo sort(char* atr);

    Cheltuieli& swap(int i, int j);

    Service filterInteger(int atr);

    Service filterChar(char* atr);

    Service& operator=(const Service& s);

    friend ostream &operator<<(ostream& os, Service& s);
};

#endif //LAB7_SERVICE_H
