#include <iostream>
using namespace std;
#ifndef LAB4_SQUARE_H
#define LAB4_SQUARE_H
class Square {
private:
    int x;
    int y;
public:
    Square();//Constructor general
    Square(int x, int y);//Constructor implicit
    Square(const Square &s);//Constructor de copiere. IN: un obiect de tip Square
    ~Square();//Deconstructor
    int getLatura();//getter pentru atributul privat latura
    void setLatura(int x, int y);//setter pentru acelasi atribut
    int getArea();//metoda de calcul al ariei unui patrat
    int getPerimeter();//metoda de calcul al perimetrului
    friend ostream &operator<<(ostream &os, Square& s);
    bool operator==(const Square& s);
};

#endif //LAB4_SQUARE_H

