//
// Created by Darius on 5/3/2023.
//
#include <vector>
#include "../domain/entitate.h"

using namespace std;
#ifndef LAB9_REPOSITORY_H
#define LAB9_REPOSITORY_H
class Repo {
protected:
    vector<Produs> elems;
    int nrElems;
public:
    Repo();
    ~Repo();
    void add(const Produs& p);
    void update(const Produs& p, int pos);
    void remove(int pos);
    int size();
    Produs& getAt(int pos);
    friend ostream &operator<<(ostream& os, const Repo& r);
};
#endif //LAB9_REPOSITORY_H
