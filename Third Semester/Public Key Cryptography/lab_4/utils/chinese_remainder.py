# Algorithm for solving systems of congruences.
#
# Consider the system
#
# x ≡ a₁ (mod n₁)
# x ≡ a₂ (mod n₂)
# ...
# x ≡ aₖ (mod nₖ)
#
# Where a₁, a₂, ..., aₖ are the remainders, and n₁, n₂, ..., nₖ are the moduls; (ni, nj) = 1
# The system has a unique solution modulo N = n1*n2...nr namely
# x = (a₁ * N₁ * K₁⁻¹ + a₂ * N₂ * K₂⁻¹ + ... + aₖ * Nₖ * Kₖ⁻¹) mod N
# where Ni = N/ni, Ki = N^(−1)i mod ni.

from utils.maths import *


def chinese_remainder(congruences):
    if len(congruences) < 2:
        return []
    # Check all the remainders to be coprime
    # Storing the remainders and divisors into 2 separated lists
    remainders = []
    divisors = []
    for (a, n) in congruences:
        remainders.append(a)
        divisors.append(n)

    # Compute N = n1 * n2 * ... nr
    N = divisors[0] * divisors[2]

    divisors[1], divisors[3] = divisors[3], divisors[1]
    # Compute N1, N2...
    Ns = []
    for divisor in divisors:
        Ns.append(N // divisor)
    Ns[1], Ns[2] = Ns[2], Ns[1]

    # Compute K1, K2, ...
    Ks = []
    for i in range(0, len(Ns)):
        Ks.append(mod_inverse(Ns[i], divisors[i]))
    x = [(remainders[0] * Ns[0] * Ks[0] + remainders[2] * Ns[2] * Ks[2]) % N,
         (remainders[1] * Ns[0] * Ks[0] + remainders[3] * Ns[2] * Ks[2]) % N,
         (remainders[0] * Ns[0] * Ks[0] + remainders[3] * Ns[2] * Ks[2]) % N,
         (remainders[1] * Ns[0] * Ks[0] + remainders[2] * Ns[2] * Ks[2]) % N]
    return x
