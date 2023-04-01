#include <iostream>
using namespace std;

#include "test.h"
#include "Square.h"
#include "Repo.h"
#include "UI.h"

int main() {
    cout << "Starting...\n";
    testAll();
    cout << "All tests passed!\n-------\n";
    UI ui = UI();
    ui.runMenu();
    return 0;
}
