from utils.maths import mod_inverse


def tonelli_shanks(a, p):
    """
    Tonelli-Shanks algorithm to find square roots modulo an odd prime p.
    """
    if pow(a, (p - 1) // 2, p) != 1:
        return None  # No square root exists

    q, s = p - 1, 0
    while q % 2 == 0:
        q //= 2
        s += 1

    z = 2
    while pow(z, (p - 1) // 2, p) != p - 1:
        z += 1

    c = pow(z, q, p)
    r = pow(a, (q + 1) // 2, p)
    t = pow(a, q, p)
    m = s

    while t != 1:
        i, b = 1, pow(t, 2, p)
        while b != 1:
            i += 1
            b = pow(b, 2, p)

        c = pow(c, pow(2, m - i - 1, p), p)
        r = (r * c) % p
        t = (t * pow(c, 2, p)) % p
        m = i

    return r, p - r


def solve_system(congruences):
    """
    Solve a system of congruences using the Chinese Remainder Theorem.
    """
    M = 1
    for _, modulus in congruences:
        M *= modulus

    x = 0
    for a, modulus in congruences:
        Mi = M // modulus
        inv_Mi = mod_inverse(Mi, modulus)
        x += a * Mi * inv_Mi

    return x % M


def solve_squared_system_tonelli_shanks(congruences):
    solutions = []
    for a, p in congruences:
        root = tonelli_shanks(a, p)
        if root is not None:
            solutions.append(root)
    return solutions
