//
// Created by Darius on 3/23/2023.
//
#include "Colection.h"


/*
 * Desc: Dublează dimensiunea in memorie a vectorilor alocati dinamic in clasa Collection
 * In:
 * Out:
 * */
void Collection::resize() {
    this->capacity *= 2;
    TElem *newElems = new TElem[this->capacity];
    int *newOcc = new int[this->capacity];
    int i;
    for (i = 0; i < this->distinctelements; i++) {
        newElems[i] = this->elements[i];
        newOcc[i] = this->occurrences[i];
    }
    delete[] elements;
    delete[] occurrences;
    this->elements = newElems;
    this->occurrences = newOcc;
}

Collection::Collection() {
    this->distinctelements = 0;
    this->capacity = 10;
    this->elements = new TElem[this->capacity];
    this->occurrences = new int[this->capacity];
}

Collection::~Collection() {
    if (this->elements) {
        delete[] this->elements;
        delete[] this->occurrences;
    }
}

int Collection::size() {
    int i;
    int lenght = 0;
    for (i = 0; i < this->distinctelements; i++)
        lenght += this->occurrences[i];
    return lenght;
}

/*
 * Desc: Adaugarea unui element dat ca parametru in lista de elemente
 * In: elem - parametru de tip TElem
 * Out:
 * */
void Collection::add(TElem elem) {
    int i;
    i = 0;
    int poz;
    poz = -1;
    while (i < this->distinctelements && poz == -1) {
        if (elem == this->elements[i])
            poz = i;
        i++;
    }

    if (poz == -1) {
        if (this->distinctelements == this->capacity)
            resize();
        this->elements[this->distinctelements] = elem;
        this->occurrences[this->distinctelements] = 1;
        this->distinctelements++;
    } else {
        this->occurrences[poz]++;
    }
}

/*
 * Desc: Cautarea unul element dat ca parametru in lista de elemente
 * In: elem - parametru de tip Elem
 * Out: true - in cazul in care s-a gasit elementul in lista,
 *      false  - altfel
 * */

bool Collection::search(TElem elem) {
    int i;
    for (i = 0; i < this->distinctelements; i++)
        if (this->elements[i] == elem)
            return true;
    return false;
}

/*
 * Desc: Stergerea unul element dat ca parametru din lista de elemente
 * In: elem - un parametru de tip TElem care trebuie sters din lista
 * Out: true - in cazul in care se sterge elem din lista de elemente
 *      false - in caz contrar
 * */
bool Collection::remove(TElem elem) {
    int i = 0;
    int poz = -1;
    while (i < this->distinctelements && poz == -1) {
        if (this->elements[i] == elem)
            poz = i;
        i++;
    }
    if (poz == -1)
        return false;
    else {
        if (this->occurrences[poz] > 1) {
            this->occurrences[poz]--;
            return true;
        } else {
            this->elements[poz] = this->elements[this->distinctelements - 1];
            this->occurrences[poz] = this->occurrences[this->distinctelements - 1];
            this->distinctelements--;
            return true;
        }
    }
}

/*
 * Desc: Returneaza numarul de aparitii ale lui elem in colectie
 * In: Parametrul elem de tip TElem
 * Out: Numarul de aparitii ale lui elem
 * */
int Collection::nroccurrences(TElem elem) {
    int i;
    int poz = -1;
    while (i < this->distinctelements && poz == -1) {
        if (elem == this->elements[i])
            poz = i;
        i++;
    }
    if (poz != -1)
        return this->occurrences[poz];
    else return 0;
}

/*
 * Desc: Returneaza elementului din listă care se gaseste pe pozitia dată ca parametru
 * In: position - parametru de tip intreg, reprezentand pozitia din lista de elemente
 * Out: elemenetul de pe pozitia position din lista de elemente
 *      -1 in cazul în care pe pozitia dată ca parametrul nu există un element
 * */
int Collection::getAt(int position) {
    if (position < this->distinctelements && position > -1)
        return this->elements[position];
    return -1;
}

bool Collection::operator==(const Collection &c) {
    if (this->distinctelements != c.distinctelements)
        return false;
    if (this->capacity != c.capacity)
        return false;
    int i;
    for (i = 0; i < this->distinctelements; i++) {
        if (this->elements[i] != c.elements[i])
            return false;
        if (this->occurrences[i] != c.occurrences[i])
            return false;
    }
    return true;
}

Collection &Collection::operator=(const Collection &c) {
    this->distinctelements = c.distinctelements;
    this->capacity = c.capacity;
    int i;
    for (i = 0; i < c.distinctelements; i++) {
        this->elements[i] = c.elements[i];//copierea elementelor din c in this
        this->occurrences[i] = c.occurrences[i];
    }
}

ostream &operator<<(ostream &os, Collection &r) {
    os << "Bancnote: ";
    int i;
    for (i = 0; i < r.distinctelements; i++)
        os << r.elements[i] << " ";
    os << "\n";

    os << "Nr. Aparitii: ";
    for (i = 0; i < r.distinctelements; i++)
        os << r.occurrences[i] << " ";
    os << "\n";

    os << "Nr. Elem. Distincte: " << r.distinctelements << "\n";
    os << "Capacitate: " << r.capacity << "\n";
}

int Collection::getDistincteElements() {
    return this->distinctelements;
}

int Collection::getOccurences(int poz) {
    if (poz > -1 && poz < this->distinctelements)
        return this->occurrences[poz];
    else return -1;
}

Collection::Collection(TElem *elements, int *occurences, int distinctelements, int capacity) {
    this->capacity = capacity;
    this->distinctelements = distinctelements;
    for(int i = 0; i < distinctelements; i++) {
        this->elements[i] = elements[i];
        this->occurrences[i] = occurences[i];
    }
}

void Collection::sort() {
    for (int i = 0; i < this->distinctelements - 1; ++i) {
        for (int j = i + 1; j < this->distinctelements; ++j) {
            if(this->elements[i] <= this->elements[j]) {
                int aux = this->elements[i];
                int aux2 = this->occurrences[i];
                this->elements[i] = this->elements[j];
                this->elements[j] = aux;
                this->occurrences[i] = this->occurrences[j];
                this->occurrences[j] = aux2;
            }
        }
    }
}