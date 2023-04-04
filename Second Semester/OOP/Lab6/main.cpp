#include "UI.h"
#include "test.h"
using namespace std;

int main() {
    cout << "Starting...\n";
    testAll();
    cout << "All test passed!\n";
    Repo r = Repo();
    UI ui = UI(r);
    ui.runMenu();
    return 0;
}
