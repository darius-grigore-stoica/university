//
// Created by Darius on 3/23/2023.
//

#ifndef LAB2_TRANZACTIE_H
#define LAB2_TRANZACTIE_H


#include "Colection.h"
#include <iostream>
using namespace std;

typedef int TElem;
class Tranzactie {
private:
    int id = 0;
    int idTransaction ;
    Collection elements;
    TElem suma;
public:
    Tranzactie();
    Tranzactie(Collection& elements, TElem suma);
    ~Tranzactie();
    friend ostream& operator<<(ostream& os, Tranzactie& r);
    Tranzactie& operator=(const Tranzactie& t);
    bool operator==(const Tranzactie& t);
};
#endif //LAB2_TRANZACTIE_H
