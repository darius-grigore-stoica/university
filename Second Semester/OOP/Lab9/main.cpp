#include <iostream>
#include "App/tests/test.h"
#include "App/UI/UI.h"
using namespace std;

int main() {
    Test t;
    t.testAll();
    cout << "Passed\n";
    UI ui = UI();
    ui.runMenu();
    return 0;
}
