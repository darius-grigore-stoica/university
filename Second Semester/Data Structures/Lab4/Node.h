//
// Created by camel on 4/9/2023.
//

#ifndef CURS7_P_NODE_H
#define CURS7_P_NODE_H

template <typename T>
class List;

template <class T>
class Node {
private:
    T info;
    Node<T>* next;
    Node<T>* prev;
public:
    Node(T info, Node<T>* next) : info(info), next(next), prev(prev){}
    Node(T info) : info(info){ next= nullptr; prev = nullptr;}
    friend class List<T>;

    Node(int i, void *pVoid, void *pVoid1) {

    }
};



#endif //CURS7_P_NODE_H
