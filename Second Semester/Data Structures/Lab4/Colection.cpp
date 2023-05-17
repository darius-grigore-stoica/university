//
// Created by Darius on 3/23/2023.
//
#include "Colection.h"

Collection::Collection() {
    this->distinctelements = 0;
    this->elements = List<TElem>();
    this->occurrences = List<int>();
}

int Collection::size() {
    int i;
    int lenght = 0;
    for (i = 0; i < this->distinctelements; i++)
        lenght += this->occurrences.getAt(i);
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
        if (elem == this->elements.getAt(i))
            poz = i;
        i++;
    }

    if (poz == -1) {
        this->elements.push_back(elem);
        this->occurrences.push_back(1);
        //this->occurrences.insert(this->distinctelements, 1);
        this->distinctelements++;
    } else {
        int value = this->occurrences.getAt(poz);
        this->occurrences.update(value + 1, poz);
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
        if (this->elements.getAt(i) == elem)
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
        if (this->elements.getAt(i) == elem)
            poz = i;
        i++;
    }
    if (poz == -1)
        return false;
    else {
        if (this->occurrences.getAt(poz) > 1) {
            this->occurrences.update(poz, this->occurrences.getAt(poz) - 1);

            return true;
        } else {
            this->elements.update(poz, this->elements.getAt(this->distinctelements - 1));
            this->occurrences.update(poz, this->occurrences.getAt(this->distinctelements - 1));
            this->distinctelements--;
            return true;
        }
    }
}

/*
 * Desc: Returneaza numarul de aparitii ale lui elem in Colectie
 * In: Parametrul elem de tip TElem
 * Out: Numarul de aparitii ale lui elem
 * */
int Collection::nroccurrences(TElem elem) {
    int i = 0;
    int poz = -1;
    while (i < this->distinctelements && poz == -1) {
        if (elem == this->elements.getAt(i))
            poz = i;
        i++;
    }
    if (poz != -1)
        return this->occurrences.getAt(poz);
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
        return this->elements.getAt(position);
    return -1;
}

bool Collection::operator==(const Collection &c) {
    if (this->distinctelements != c.distinctelements)
        return false;
    int i;
    for (i = 0; i < this->distinctelements; i++) {
        if (this->elements.getAt(i) != c.elements.getAt(i))
            return false;
        if (this->occurrences.getAt(i) != c.occurrences.getAt(i))
            return false;
    }
    return true;
}

Collection &Collection::operator=(const Collection &c) {
    this->distinctelements = c.distinctelements;
    int i;
    for (i = 0; i < c.distinctelements; i++) {
        if(this->elements.getAt(i) == -1)
            this->elements.push_back(c.elements.getAt(i));
        else this->elements.update(c.elements.getAt(i), i);
        if(this->occurrences.getAt(i) == -1)
            this->occurrences.push_back(c.occurrences.getAt(i));
        else this->occurrences.update(c.occurrences.getAt(i), i);
    }
    return *this;
}

ostream &operator<<(ostream &os, Collection &r) {
    os << "Bancnote: ";
    int i;
    for (i = 0; i < r.distinctelements; i++)
        os << r.elements.getAt(i) << " ";
    os << "\n";

    os << "Nr. Aparitii: ";
    for (i = 0; i < r.distinctelements; i++)
        os << r.occurrences.getAt(i) << " ";
    os << "\n";

    os << "Nr. Elem. Distincte: " << r.distinctelements << "\n";
    return os;
}

int Collection::getDistincteElements() const {
    return this->distinctelements;
}

int Collection::getOccurences(int poz) {
    if (poz > -1 && poz < this->distinctelements)
        return this->occurrences.getAt(poz);
    else return -1;
}


void Collection::sort() {
    for (int i = 0; i < this->distinctelements - 1; ++i) {
        for (int j = i + 1; j < this->distinctelements; ++j) {
            if(this->elements.getAt(i) <= this->elements.getAt(j)) {
                int aux = this->elements.getAt(i);
                int aux2 = this->occurrences.getAt(i);
                this->elements.update(this->elements.getAt(j), i);
                this->elements.update(aux, j);
                this->occurrences.update(this->occurrences.getAt(j), i);
                this->occurrences.update(aux2, j);
            }
        }
    }
}

Collection::Collection(List<TElem> elements, List<int> occurences, int distinctelements) {
    this->distinctelements = distinctelements;
    for (int i = 0; i < distinctelements; i++) {
        this->elements.update(i, elements.getAt(i));
        this->occurrences.update(i, occurences.getAt(i));
    }
}

Collection::~Collection()= default;
