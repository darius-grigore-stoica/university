//
// Created by camel on 4/3/2023.
//

#ifndef CURS6_VECTORDINAMIC_H
#define CURS6_VECTORDINAMIC_H

template <class T> class VectorDinamic{
private:
    int capacity;
    int nrElems;
    T* elems;
    void resize(){
        this->capacity *= 2;
        T *newElems = new T[this->capacity];
        int i;
        for(i = 0; i < this->nrElems; i++)
            newElems[i] = this->elems[i];
        delete[] this->elems;
        this->elems = newElems;
    };
public:
    /// Constructor cu parametri
    /// \param capacitate - se aloca memorie pentru un vector de capacitate elemente
    /// \raise invalid input exception, daca capacitate <= 0
    VectorDinamic(){
        this->capacity = 10;
        this->elems = new T [this->capacity];
        this->nrElems = 0;
    };
    VectorDinamic (int capacitate){
        if(capacitate <= 0)
            throw std::invalid_argument("capacitatea trebuie sa fie un numar pozitiv");
        this->capacity = capacitate;
        this->elems = new T [capacitate];
        this->nrElems = 0;
    };
    ///
    /// \return numarul de elemente din vectorul dinamic
    int size() const{
        return this->nrElems;
    };

    ///
    /// \param i, 0 <= i < numarul de elemente din vector
    /// \return elementul de pe pozitia i din vector
    /// \raise invalid input exception, daca i nu este o pozitie valida
    T getAt(int i) const{
        return this->elems[i];
    };

    /// actualizeaza elementul de pe pozitia i cu valoarea e
    /// \param i, 0 <= i < numarul de elemente din vector
    /// \param e, element de tip TElem
    /// \return elementul de pe pozitia i care a fost inlocuit cu e
    /// \raise invalid input exception, daca i nu este o pozitie valida
    T update(int i, T e){
        this->elems[i] = e;
        return this->elems[i];
    };

    /// adauga un element la sfarsitul vectorului
    /// \param e, element de tip TElem, elem adaugat la final
    void push_back(T e){
        if (this->nrElems == this->capacity)
            resize();
        this->elems[this->nrElems] = e;
        this->nrElems++;
    };


    /// adauga un element pe pozitia i
    /// \param i, 0 <= i < numarul de elemente din vector
    /// \param e, element de tip TElem
    /// \raise invalid input exception, daca i nu este o pozitie valida
    void addAt(int i, T e){
        if(i < -1 || i >= this->nrElems)
            throw std::invalid_argument("pozitie invalida pentru adaugare");
        this->elems[i] = e;
    };

    /// stergem elementul de pe pozitia i
    /// \param i, 0 <= i < numarul de elemente din vector
    /// \return elementul de pe pozitia i
    T sterge(int i){
        if(i < 0 || i >= nrElems)
            throw std::invalid_argument("pozitie invalida pentru stergere");
        int p;
        for(p = i; p < this->nrElems - 1; p++)
            this->elems[p] = this->elems[p + 1];
        this->nrElems--;
        return this->elems[i];
    };

    /// destructor - dealoca memoria
    ~VectorDinamic(){
        if(this->elems)
            delete[] this->elems;
    };

    /// supraincarcarea operatorului de afisare
    VectorDinamic& operator=(const VectorDinamic& v){
        int i;
        for(i = 0; i < this->nrElems; i++)
            this->elems[i] = v.getAt(i);
        return *this;
    };
};




#endif //CURS6_VECTORDINAMIC_H
