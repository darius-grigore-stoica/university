/* Generalized Fermat’s algorithm.
 * It will first consider k = 1. If not successful, then it will consider
 * k = 2, 3, . . . until getting a factor.
*/

#include <iostream>
#include <cmath>
#include <assert.h>
using namespace std;

/// Helper function to compute the greatest common divisor of a and b
/// \param a
/// \param b
/// \return integer gcd
long long gcd(long long a, long long b) {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}

/// Helper function to check if a long is a square number
/// \param n
/// \return true if n is a square number, false otherwise
bool isPerfectSquare(long long n) {
    long long sqrtN = sqrt(n);
    return (sqrtN * sqrtN == n);
}

/// Function to compute Generalized Fermat's Method
/// \param n odd composite number
/// \param k
/// \return
pair<long, long> generalizedFermat(long long n, long long k) {
    //Compute t and s^2 for the current value of k
    long long t = sqrt(n * k);
    long long sSquare = t * t - k * n;

    //Increase t while s^2 is not a square number
    while (!isPerfectSquare(sSquare)) {
        t++;
        sSquare = t * t - k * n;
    }

    //Compute the result of the problem
    long long s = sqrt(sSquare);
    long long factor1 = gcd(t - s, n);
    long long factor2 = gcd(t + s, n);

    //In case s is 0, there's no solution for the current value of k
    if (s == 0)
        return make_pair(0, 0);
    if (factor1 != 1 && factor1 != n && factor2 != 1 && factor2 != n) {
        return make_pair(factor1, factor2);
    }
    return make_pair(0, 0);
}

void testGeneralizedFermat(){
    pair<long, long> res = generalizedFermat(200819, 1);
    assert(res.first == 409 && res.second == 491);
    pair<long, long> res2 = generalizedFermat(141467, 1);
    assert(res2.first == 241 && res2.second == 587);
}


int main() {
    testGeneralizedFermat();
    cout << "All tests have been passed\n";

    long long n;
    cout << "Enter a composite number to factorize:\n";
    cin >> n;

    long long k = 1;
    pair<long, long> factor = make_pair(0, 0);

    while (factor.first == 0 && factor.second == 0) {
        factor = generalizedFermat(n, k);
        k++;
    }

    cout << "Non-trivial factor found: " << factor.first << " " << factor.second;
    return 0;
}
