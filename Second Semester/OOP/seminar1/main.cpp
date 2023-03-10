#include <iostream>
using namespace std;


void readMatrix(int nrLin, int nrCol, int mat[50][50]){
    int i;
    int j;
    for(i = 0; i < nrLin; i++)
        for(j = 0; j < nrCol; j++)
            cin >> mat[i][j];
}

void getNumberOfElements(int mat[50][50], int nrLin, int nrCol, int &nrPoz, int &nrNeg, int &nrNul){
    int i;
    int j;
    for(i = 0; i < nrLin; i++)
        for(j = 0; j < nrCol; j++) {
            if(mat[i][j] > 0)
                nrPoz++;
            else if(mat[i][j] < 0)
                nrNeg++;
            else if(mat[i][j] == 0)
                nrNul++;
            else continue;
        }
}

int main() {
    int nrLin = 0, nrCol = 0, mat[50][50];
    cin >> nrLin;
    cin >> nrCol;
    readMatrix(nrLin, nrCol, mat);
    int nrPoz = 0, nrNeg = 0, nrNul = 0;
    getNumberOfElements(mat, nrLin, nrCol, nrPoz, nrNeg, nrNul);
    cout << nrPoz << endl << nrNeg << endl << nrNul;
    return 0;
}
