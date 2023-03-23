//
// Created by Darius on 3/23/2023.
//

#ifndef SEM2_COLECTION_H
#define SEM2_COLECTION_H

#endif //SEM2_COLECTION_H
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
    ~Collection();
    void add(TElem elem);
    bool remove(TElem elem);
    bool search(TElem elem);
    int size();
    int nroccurrences(TElem elem);
    int getAt(int position);
};