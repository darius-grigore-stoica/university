//
// Created by Darius on 5/11/2023.
//
#include "Node.h"
#include "List.h"
#include "assert.h"
#ifndef SEM5_TEST_H
#define SEM5_TEST_H
class Test{
public:
    void testInsert(){
        List<int> l;
        l.push_back(5);
        l.push_back(10);
        l.insert(7, 1);
        assert(l.getAt(1) == 7);
    };
    void testPush(){
        List<int> l;
        l.push_back(5);
        l.push_back(10);
        l.push(1);
        assert(l.getAt(0) == 1);
    };
    void testPushBack(){
        List<int> l;
        l.push_back(5);
        assert(l.size() == 1);
        l.push_back(10);
        assert(l.size() == 2);
        assert(l.getAt(1) == 10);
    };
    void testRemove(){
        List<int> l;
        l.push_back(5);
        l.push_back(10);
        l.push_back(15);
        l.remove(1);
        assert(l.getAt(0) == 5);
        assert(l.getAt(1) == 15);
        assert(l.size() == 2);
    };
    void testUpdate(){
        List<int> l;
        l.push_back(5);
        l.push_back(10);
        l.push_back(15);
        l.update(-15, 2);
        assert(l.getAt(2) == -15);
    };
    void testSize(){
        List<int> l;
        assert(l.size() == 0);
        l.push_back(5);
        l.push_back(10);
        assert(l.size() == 2);
        l.push_back(15);
        assert(l.size() == 3);
    };

    void testgetAt(){
        List<int> l;
        l.push_back(5);
        l.push_back(10);
        assert(l.getAt(1) == 10);
    }

    void testAll(){
        testSize();
        testgetAt();
        testUpdate();
        testRemove();
        testInsert();
        testPush();
        testPushBack();
    }
};
#endif //SEM5_TEST_H
