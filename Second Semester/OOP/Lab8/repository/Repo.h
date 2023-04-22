//
// Created by Darius on 4/12/2023.
//
#include "../domain/Cheltuieli.h"

#ifndef LAB7_REPO_H
#define LAB7_REPO_H

class Repo {
private:
    int capacity;
    Cheltuieli *elems;
    int nrElems;

    void resize();

public:
    Repo();

    Repo(int capacity, Cheltuieli *elems, int nrElems);

    Repo(const Repo &r);

    ~Repo();

    void add(const Cheltuieli &elem);

    void update(int pos, Cheltuieli &elem);

    void remove(int pos);

    Repo &operator=(const Repo &r);

    Cheltuieli getAt(int pos);

    int getSize();

    Cheltuieli& swap(int i, int j);

    friend ostream &operator<<(ostream& os, Repo& s);
};

#endif //LAB7_REPO_H
