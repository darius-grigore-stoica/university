//
// Created by Darius on 3/23/2023.
//

#ifndef SEM2_COLECTION_H
#define SEM2_COLECTION_H

#include <iostream>
using namespace std;
typedef int TElem;
class Collection {
private:
    TElem *elements;
    int *occurrences;
    int distinctelements;
    int capacity;
    void resize();
public:
    Collection();
    Collection(TElem* elements, int* occurences, int distinctelements, int capacity);
    ~Collection();
    void add(TElem elem);
    void sort();
    int getDistincteElements();
    int getOccurences(int poz);
    bool remove(TElem elem);
    bool search(TElem elem);
    int size();
    int nroccurrences(TElem elem);
    int getAt(int position);
    Collection& operator=(const Collection& c);
    bool operator==(const Collection& c);
    friend ostream& operator<<(ostream& os, Collection& r);
};

#endif //SEM2_COLECTION_H