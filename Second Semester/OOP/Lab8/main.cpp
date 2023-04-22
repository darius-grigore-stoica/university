#include "tests/Test.h"
#include "UI/UI.h"

int main() {
    Test t;
    t.testCheltuieli();
    t.testRepo();
    t.testService();
    UI ui = UI();
    ui.runMenu();
    return 0;
}
