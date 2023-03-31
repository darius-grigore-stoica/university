#include "Square.h"
#include <iostream>

Square::Square(){
    this->x = 0;
    this->y = 0;
};

Square::Square(int x, int y) {
    this->x = x;
    this->y = y;
}

Square::Square(const Square &s) {
    this->x = s.x;
    this->y = s.y;
}
Square::~Square() = default;

void Square::setLatura(int x, int y) {
    this->x= x;
    this->y = y;
}

int Square::getLatura() {
    int result = this->x - this->y;
    if(result > 0)
        return this->x - this->y;
    else
        return this->y - this->x;
}

int Square::getArea() {
    int result = (this->x - this->y) * (this->x - this->y);
    if(result < 0)
        return -1 * result;
    else return result;
}

int Square::getPerimeter() {
    int result = 4 * (this->x - this->y);
    if(result < 0)
        return -1 * result;
    else return result;
}

ostream &operator<<(ostream &os, Square& s) {
    os << "PATRAT:" << s.getLatura() << " " << "ARIA: " << s.getArea() << " " << "PERIMETRU: " << s.getPerimeter() << "\n";
    return os;
}

bool Square::operator==(const Square &s) {
    if(s.x != this->x)
        return false;
    if(s.y != this->y)
        return false;
    return true;
}
