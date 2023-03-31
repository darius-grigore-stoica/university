#include "Square.h"
#include <iostream>

Square::Square(){
    //std::cout << "Constructor Implicit" << std::endl;
    this->latura = 0;
};

Square::Square(int latura) {
    this->latura = latura;
}

Square::Square(const Square &s) {
    this->latura = s.latura;
}
Square::~Square() {
    //std::cout << "Patrat distrus";
}

void Square::setLatura(int latura) {
    this->latura = latura;
}

int Square::getLatura() {
    return this->latura;
}

int Square::getArea() {
    return this->latura * this->latura;
}

int Square::getPerimeter() {
    return 4 * this->latura;
}
