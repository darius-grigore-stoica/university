//
// Created by Darius on 3/25/2023.
//
#include "ATM.h"

ATM::ATM() {
    this->colectie = Collection();
    this->tranzactii = Repo();
}

ATM::ATM(Collection &c, Repo &t) {
    this->colectie = c;
    this->tranzactii = t;
}

ATM::~ATM() = default;

//bool ATM::backtracking(Collection &c, int result[10][10], int remaining, int index) {
//    int counts[10];
//    if (remaining == 0) {
//        //cout << "Combinatii de a scrie suma: ";
//        for (int i = 0; i < c.size(); i++) {
//            for (int j = 0; j < counts[i]; j++) {
//                result[i][j] = c.getAt(i);
//            }
//        }
//    }
//    if (index >= c.size()) {
//        return result;
//    }
//    for (int i = index; i < c.size(); i++) {
//        if (remaining >= c.getAt(i)) {
//            counts[i]++;
//            backtracking(c, result, remaining - c.getAt(i), i);
//            counts[i]--;
//        }
//    }
//};
//
bool ATM::backtracking(Collection &c, int sum, Collection &newCollection, int iteratie) {
    if(iteratie > c.getDistincteElements())
        return false;//in cazul in care iteratie depaseste numarul de bancnote din colectie, oprim executarea recursiva
    int suma = sum;
    int i = iteratie;//initializam prima pozitie a elementului cu care incepem evaluarea solutiei actuale

    while(suma > 0 && i < 10){
        TElem item = c.getAt(i);//preluam elementul curent de pe pozitia i initializata mai sus
        int occ = c.getOccurences(i);//si numarul de aparitii al acestui element
        int scadere = 0;//contorizam de care ori am scazut din suma elemetul curent
        while(suma - item >= 0 && scadere < occ) {
            suma -= item;//si scadem din suma pana la egalarea acesteia cu 0 sau atigerea unei valori negative
            scadere++;
        }
        for(int p = 0; p < scadere; p++)
            newCollection.add(item);//adaugam in colectia nou formata elementul curent, de atatea ori de cate am scazut din suma
        i++;
    }

    if(suma == 0)
        return true;
    else {
        iteratie++;//in cazul in care nu am gasit o solutie
        newCollection = Collection();//cautam solutia in dreapta elementului de la care am inceput compunerea solutiei
        backtracking(c, sum, newCollection, iteratie);//si apelam recursiv algoritmul
    }
}