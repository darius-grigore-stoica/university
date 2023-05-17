//
// Created by Darius on 5/11/2023.
//
#include "Node.h"
#include "stdexcept"

#ifndef SEM5_LDI_H
#define SEM5_LDI_H

template<class T>
class List {
private:
    Node<T> *head;
    Node<T> *tail;
    int nrElems;
public:
    List() {
        this->head = nullptr;
        this->tail = nullptr;
        this->nrElems = 0;
    }

    void insert(T e, int pos);

    void push_back(T e);

    void push(T e);

    void remove(int pos);

    T update(T e, int pos);

    int size();

    T getAt(int pos) const;

};

template<class T>
T List<T>::getAt(int pos) const {
//    if(pos < 0 || pos >= this->nrElems)
//        throw std::invalid_argument("Pozitie invalida pentru extragere");
    int noE = 0;
    Node<T> *crt = this->head;
    while (noE < pos && crt != this->tail) {
        crt = crt->next;
        noE++;
    }
    if (crt)
        return crt->info;
    else return -1;
}

template<class T>
void List<T>::push_back(T e) {
    Node<T> *newNode = new Node<T>(e, nullptr, nullptr);
    if (this->head == nullptr) {
        this->head = newNode;
        this->nrElems++;
    } else {
        Node<T> *crt = this->head;
        while (crt->next != nullptr) {
            crt = crt->next;
        }
        crt->next = newNode;
        newNode->prev = crt;
        this->tail = newNode;
        this->nrElems++;
    }
}

template<class T>
void List<T>::push(T e) {
    Node<T> *newNode = new Node<T>(e, nullptr, nullptr);
    newNode->next = this->head;
    this->head->prev = newNode;
    this->head = newNode;//mutam head-ul la elementul nou adaugat
    this->nrElems++;
}

template<class T>
void List<T>::remove(int pos) {
    if (pos < 0 || pos >= this->nrElems)
        throw std::invalid_argument("Pozitie invalida pentru stergere");

    Node<T> *crt = this->head;
    if (pos == 0)
        this->head = crt->next;
    else {
        int noE = 0;
        crt = this->head;
        while (noE < pos) {
            crt = crt->next;
            noE++;
        }
        crt->prev->next = crt->next;
        crt->next->prev = crt->prev;
        this->nrElems--;
    }
}

template<class T>
T List<T>::update(T e, int pos) {
    if(this->head == nullptr) {
        push_back(e);
        return e;
    }
    else {
        Node<T> *crt = this->head;
        int noE = 0;
        while (noE < pos && crt->next != nullptr) {
            crt = crt->next;
            noE++;
        }
        if (crt->next != nullptr) {
            T old_value = crt->info;
            crt->info = e;
            return old_value;
        }
    }
}

template<class T>
int List<T>::size() {
    return this->nrElems;
}

template<class T>
void List<T>::insert(T e, int pos) {
    Node<T> *newNode = new Node<T>(e, nullptr, nullptr);
    if (this->head == nullptr) {
        this->head = newNode;
        this->nrElems++;
    } else {
        Node<T> *crt = this->head;
        int noE = 0;
        while (noE <= pos && crt->next != nullptr) {
            crt = crt->next;
            noE++;
        }
        Node<T> *prv = crt->prev;
        newNode->prev = prv;
        newNode->next = crt;
        prv->next = newNode;
        crt->prev = newNode;
        this->nrElems++;
    }

};

#endif //SEM5_LDI_H
