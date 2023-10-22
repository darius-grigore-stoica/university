# Implement 3 different algorithms for computing the greatest common divisor of 2 natural numbers.
# One of the algorithms should work for numbers of arbitrary size!
# Perform a comparative running-time analysis of these algorithms for a set of at least 10 inputs (use
# appropriate time units in order to differentiate the algorithms).

import time


# Decorator function to calculate execution time in milliseconds
def timing_decorator(func):
    def wrapper(*args, **kwargs):
        start_time = time.time()
        result = func(*args, **kwargs)
        end_time = time.time()
        execution_time_ms = (end_time - start_time) * 1000
        print(f"{func.__name__} took {execution_time_ms:.5f} ms")
        return result

    return wrapper


# Euclidean algorithm to find GCD
@timing_decorator
def euclidean_gcd(a, b):
    if a < 0 or b < 0:
        a = abs(a)
        b = abs(b)
    if a == 0 or b == 0:
        return 0
    while b:
        a, b = b, a % b
    return a


# Multiple subtractions algorithm to find GCD
@timing_decorator
def subtractions_gdc(a, b):
    if a < 0 or b < 0:
        a = abs(a)
        b = abs(b)
    if a == 0 or b == 0:
        return 0
    while a != b:
        if a > b:
            a -= b
        else:
            b -= a
    return a


# Helper function to find prime factors of a number
def find_prime_factors(n):
    factors = []
    divisor = 2

    while divisor <= n:
        if n % divisor == 0:
            factors.append(divisor)
            n //= divisor
        else:
            divisor += 1

    return factors


# Prime Factorization algorithm to find GCD
@timing_decorator
def prime_factorization_gcd(a, b):
    if a == 0 or b == 0:
        return 0
    else:
        a_factors = find_prime_factors(a)
        b_factors = find_prime_factors(b)

        common_factors = list(set(a_factors) & set(b_factors))

        gcd = 1
        for factor in common_factors:
            gcd *= factor

        return gcd


def run_time_analysis(a, b):
    e = euclidean_gcd(a, b)
    s = subtractions_gdc(a, b)
    p = prime_factorization_gcd(a, b)
    print(e, s, p)


if __name__ == '__main__':
    inputs = [(9, 3), (21241, 2), (312141, 123141), (0, 1231231)]
    for (a, b) in inputs:
        run_time_analysis(a, b)
