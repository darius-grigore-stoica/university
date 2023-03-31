#include "Domain/UI/UI.h"

using namespace std;

int main() {
    Collection c = Collection();
    Repo t = Repo();
    ATM a = ATM();
    UI ui = UI();
    ui.runMenu();
    return 0;
}