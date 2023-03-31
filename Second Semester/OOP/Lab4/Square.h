#ifndef LAB4_SQUARE_H
#define LAB4_SQUARE_H

#endif //LAB4_SQUARE_H

class Square {
private:
    int latura;
public:
    Square();//Constructor general
    Square(int latura);//Constructor implicit
    Square(const Square &s);//Constructor de copiere. IN: un obiect de tip Square
    ~Square();//Deconstructor
    int getLatura();//getter pentru atributul privat latura
    void setLatura(int latura);//setter pentru acelasi atribut
    int getArea();//metoda de calcul al ariei unui patrat
    int getPerimeter();//metoda de calcul al perimetrului
};
