//
// Created by Darius on 4/6/2023.
//
#include <queue>

using namespace std;
#ifndef SEM3_COADA_H
#define SEM3_COADA_H
typedef int TElem;

typedef int TElem;


class Coada {
private:
    TElem *elements;
    int start, end;
    int capacity;
    void resize();
public:
    Coada();
    void add(TElem elem);
    TElem remove();
    bool isEmpty();
    int size();
    ~Coada();
};

#endif //SEM3_COADA_H
