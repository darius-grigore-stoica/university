#include "Test.h"
#include "Coada.h"
#include <iostream>
#include <fstream>

using namespace std;

ifstream fin("matrice.in");

int main() {

    cout << "Starting..." << endl;
    Test t = Test();
    t.TestAll();
    cout << "All test passed!" << endl;

    int viz[10] = {0};
    int mat[10][10];
    int i, j;
    for (i = 1; i <= 6; i++)
        for (j = 1; j <= 6; j++)
            fin >> mat[i][j];
    int root;
    fin >> root;
    Coada c = Coada();
    c.add(root);
    viz[root] = 1;
    while (!c.isEmpty()) {
        cout << "intrat1" << endl;
        int e = c.remove();
        cout << e << " ";
        for (i = 1; i <= 6; i++) {
            if (mat[e][i] == 1 && viz[i] == 0) {
                cout << "intrat" << endl;
                viz[i] = 1;
                c.add(i);
            }
        }
    }
    return 0;
}
