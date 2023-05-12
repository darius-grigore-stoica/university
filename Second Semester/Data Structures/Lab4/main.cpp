#include <iostream>
#include "List.h"
#include "TestList.h"

using namespace std;

int str_cmp(string a, string b)
{
    return a.compare(b);
}

int main() {
    std::cout << "Hello, World!" << std::endl;
    TestList t;
//    t.test_push_back();
t.test_insert();
    t.test_get_at();
    t.test_update();
    return 0;
}
