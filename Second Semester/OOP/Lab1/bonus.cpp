//
// Created by Darius Stoica on 3/1/2023.
//
int RusseSum(int a, int b){
    int q = 0;
    while(a != 0){
        if(a % 2 == 1) {
            q += b;
        }
        a /= 2;
        b *= 2;
    }
    return q;
}