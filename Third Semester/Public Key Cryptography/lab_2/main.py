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

def extended_gcd(a, b):
    if a == 0:
        return b, 0, 1
    else:
        g, x, y = extended_gcd(b % a, a)
        return g, y - (b // a) * x, x


def mod_inverse(a, m):
    g, x, y = extended_gcd(a, m)
    if g != 1:
        raise ValueError("The modular inverse does not exist\n")
    else:
        return x % m


def chinese_remainder(congruences):
    # Check all the remainders to be coprime
    remainders = []
    divisors = []
    for (a, n) in congruences:
        remainders.append(n)
        divisors.append(a)
    for i in range(0, len(congruences) - 1):
        for j in range(i + 1, len(congruences)):
            g, x, y = extended_gcd(remainders[i], remainders[j])
            if g != 1:
                raise ValueError("There are remainders that are not coprime\n")

    # Compute N = n1 * n2 * ... * nr
    N = 1
    for remainder in remainders:
        N *= remainder

    # Compute N1, N2...

    Ns = []
    for remainder in remainders:
        Ns.append(N // remainder)

    # Compute K1, K2, ...
    Ks = []
    for i in range(0, len(Ns)):
        Ks.append(mod_inverse(Ns[i], remainders[i]))

    x = 0
    for i in range(0, len(divisors)):
        x += divisors[i] * Ns[i] * Ks[i]

    return x % N


if __name__ == '__main__':
    congruences = [(2, 5),  (5, 11)]
    try:
        x = chinese_remainder(congruences)
        print("Solution is:", x)
    except ValueError as v:
        print(v)
