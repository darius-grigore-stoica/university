//
// Created by Darius on 4/5/2023.
//
#include "Operations.h"
#include "Repo.h"

Square Operations::getMaxim(Repo& r){
    Square max = Square(0, 0);
    queue<Square> copy = r.getElems();
    while(!copy.empty()){
        Square front = copy.front();
        if(max.getArea() < front.getArea())
            max = front;
        copy.pop();
    }
    return max;
}

void Operations::getPositive(Repo& r) {
    queue<Square> copy = r.getElems();
    while(!copy.empty()) {
        if (copy.front().isInFirst()) {
            cout << copy.front();
        }
        copy.pop();
    }
}

void Operations::getSequenceOfEquals(Repo& r, int &left, int &right) {
    int i, max;
    i = 0;
    max = -1;
    queue<Square> copy = r.getElems();//declaram o copie a cozii de elemente
    while(i < r.getNrElems()){
        bool equals = true;//declaram o variabila de validare a egalitatii intre elemente

        int left_c = i, right_c = i;//variabile intermediare, folosite pentru a determina lungimea secventei verificate curent
        while(!copy.empty() && equals){//cat timp avem elemente de comparat in coada si egalitate intre elementele din secventa curenta
            Square first, second;//preluam primul si al doilea element din ordine din coada
            first = copy.front();
            copy.pop();
            second = copy.front();
            if(!(first == second))//si daca nu sunt egale, negam variabila de validare
                equals = false;
            else right_c++;

        }

        if(right_c - left_c >= max) {//evalueam lungimea secventei curente prin comparare cu secventa maxima
            max = right_c - left_c;
            right = right_c;//iar după executarea pe toată lista, obtinem secventa cea mai lunga
            left = left_c;
        }
        i++;
    }
}

Operations::Operations() {};
