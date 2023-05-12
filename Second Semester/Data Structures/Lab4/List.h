//
// Created by camel on 4/9/2023.
//

#include "Node.h"

#ifndef CURS7_P_LIST_H
#define CURS7_P_LIST_H
#include <iostream>
using namespace std;

template <class T>
class List {
private:
    /* definiti o reprezentare inlantuita pe elementele din lista */
    Node<T>* head;
    Node<T>* tail;
public:
    /// constructor implicit
    List () {
        this->head = nullptr;
        this->tail = nullptr;
    }

    /// \return dimensiunea listei
    int size_front() const;
    int size_back() const;
    ///  verifica daca lista e vida
    /// \return true, daca lista e vida, altfel returneaza false
    bool is_empty() const;

    /// adaugare element la sfarsit
    /// \param e - elementul adaugat
    void push_back(T e);

    void push_back_tail(T e);

    ///
    /// \param i - pozitia unui elem in lista , 0<=i<size()
    /// \return  elementul de pe pozitia i
    /// \raise   exceptie daca i nu e valid
    T get_at(int i) const;

    // modifica element de pe pozitia i si returneaza vechea valoare
    //arunca exceptie daca i nu e valid
    T update(int i, T e);



    // adaugare element pe o pozitie i
    //arunca exceptie daca i nu e valid
    //void set_at(int i, T e);

    // sterge element de pe o pozitie i si returneaza elementul sters
    //arunca exceptie daca i nu e valid
    //T delete_at(int i);

    // cauta element si returneaza prima pozitie pe care apare (sau -1)
    //int search(T e)  const;

    // returnare iterator
//    Iterator iterator() const;

    ///
    /// destructor
    //~List();
    void insert(T e, int pos);
    void push_front(Node<T> *cap, T e);
};

template<class T>
T List<T>::update(int i, T e) {
    if(i < 0 || i >= size_front())
        throw invalid_argument("pozitia nu e valida");
    Node<T>* crt = this->head;
    int noE = 0;
    while (noE < i){
        crt = crt->next;
        noE++;
    }
    T old_value = crt->info;
    crt->info = e;

    return old_value;
}

template<class T>
T List<T>::get_at(int i) const {
    if(i < 0 || i >= size_front())
        throw invalid_argument("pozitia nu e valida");
    Node<T>* crt = this->head;
    int noE = 0;
    while (noE < i){
        crt = crt->next;
        noE++;
    }
    return crt->info;
}

template<class T>
void List<T>::push_back(T e) {
    Node<T>* newNode = new Node<T>(e, nullptr, nullptr);
    newNode->next = this->head;
    this->head= newNode;
}

template<class T>
void List<T>::push_back_tail(T e) {
    Node<T>* newNode = new Node<T>(e, nullptr, nullptr);
    newNode->prev = this->tail;
    this->tail->next = newNode;
    this->tail = newNode;
}


template<class T>
void List<T>::insert(T e, int pos){
    Node<T>* newNode = new Node<T>(e, nullptr, nullptr);
    Node<T>* crt = this->head;
    int noE = 0;
    while (noE < pos){
        crt = crt->next;
        noE++;
    }
    newNode->next = crt->next;
    newNode->prev = crt;
    crt->next = newNode;
    newNode->next->prev= newNode;
}

template<class T>
bool List<T>::is_empty() const {
    return this->head == nullptr;
}

template<class T>
int List<T>::size_front() const {
    int contor = 0;
    Node<T>* last = this->head;
    while(last != nullptr)
    {
        contor++;
        last = last->next;
    }
    return contor;
}

template<class T>
int List<T>::size_back() const {
    int contor = 0;
    Node<T>* last = this->tail;
    while(last != nullptr)
    {
        contor++;
        last = last->prev;
    }
    return contor;
}

#endif //CURS7_P_LIST_H
