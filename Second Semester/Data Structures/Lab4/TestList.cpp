//
// Created by camel on 4/10/2023.
//

#include "TestList.h"
#include "List.h"
#include <assert.h>

//void TestList::test_push_back()
//{
//    List<int> newList;
//    newList.push_back(1);
//    newList.push_back(2);
//    newList.push_back(3);
//    newList.push_back(4);
//    assert(newList.size() == 4);
//}
void TestList::test_insert()
{
    List<int> newList;
    newList.push_back(1);
    newList.push_back(2);
    newList.push_back(3);
    newList.insert(5, 2);
    newList.push_back(4);
    assert(newList.get_at(2) == 5);
}

void TestList::test_get_at() {
    List<int> newList;
    newList.push_back(1);
    newList.push_back(2);
    newList.push_back(3);
    newList.push_back(4);
    assert (newList.get_at(0) == 1);
    assert (newList.get_at(1) == 2);
    assert (newList.get_at(2) == 3);
    assert (newList.get_at(3) == 4);
}

void TestList::test_update() {
    List<int> newList;
    newList.push_back(1);
    newList.push_back(2);
    newList.push_back(3);
    newList.push_back(4);
    newList.update(2, 17);
    assert (newList.get_at(0) == 1);
    assert (newList.get_at(1) == 2);
    assert (newList.get_at(2) == 17);
    assert (newList.get_at(3) == 4);
}
