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
    if (head == nullptr) {
        return -1;
    }

    Node<T> *currentNode = head;
    int currentPosition = 0;
    while (currentPosition < pos && currentNode != nullptr) {
        currentNode = currentNode->next;
        currentPosition++;
    }

    if (currentNode == nullptr) {
        return -1;
    }
    else return head->info;
}

template<class T>
void List<T>::push_back(T e) {
    Node<T>* newNode = new Node<T>(e, nullptr, nullptr);
    if (head == nullptr) {
        // If the list is empty, make the new node as the head
        newNode->prev = nullptr;
        head = newNode;
    }
    else {
        Node<T> *current = head;
        // Traverse to the last node
        while (current->next != nullptr) {
            current = current->next;
        }
        // Add the new node at the end
        current->next = newNode;
        newNode->prev = current;
    }
}

template<class T>
void List<T>::push(T e) {
    Node<T>* newNode = new Node<T>(e, head, nullptr);

    if (head != nullptr) {
        (head)->prev = newNode;
    }
    head = newNode;
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
    if (head == nullptr) {
        return -1;
    }

    Node<T>* currentNode = head;
    int currentPosition = 1;

    // Traverse to the specified position
    while (currentPosition < pos && currentNode != nullptr) {
        currentNode = currentNode->next;
        currentPosition++;
    }

    if (currentNode == nullptr) {
        return -1;
    }
    // Update the value of the node
    currentNode->info = e;
    return e;
}

template<class T>
int List<T>::size() {
    return this->nrElems;
}

template<class T>
void List<T>::insert(T e, int pos) {
    if (head == nullptr) {
        throw std::invalid_argument("Lista este goala");
    }

    Node<T> *currentNode = head;
    int currentPosition = 1;

    // Traverse to the specified position
    while (currentPosition < pos && currentNode != nullptr) {
        currentNode = currentNode->next;
        currentPosition++;
    }

    if (currentNode != nullptr) {
        // Create a new node
        Node<T> *newNode = new Node<T>(e, nullptr, nullptr);
        // Insert the new node after the current node
        newNode->prev = currentNode;
        newNode->next = currentNode->next;

        if (currentNode->next != nullptr) {
            currentNode->next->prev = newNode;
        }
        currentNode->next = newNode;
        return e;
    }
};

#endif //SEM5_LDI_H
