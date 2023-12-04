def create_dict() -> dict[str, int]:
    uppercase_letters = '_ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    my_dict = {}

    for index, letter in enumerate(uppercase_letters, start=0):
        my_dict[letter] = index

    return my_dict


def create_keys() -> tuple[int, tuple]:
    pass


def is_quadratic_residue(n, p) -> int:
    # check if n is a quadratic residue modulo p
    return pow(n, (p - 1) // 2, p) == 1


# using the public key, encrypts a given plaintext
# returns the encrypted message
def encrypt(message, public_key) -> str:
    if not is_plaintext(message):
        return ""
    n = public_key
    plaintext = int.from_bytes(message.encode(), 'big')
    # ensure plaintext is a quadratic residue modulo n
    while not is_quadratic_residue(plaintext, n):
        plaintext = int.from_bytes(message.encode(), 'big')
    ciphertext = pow(plaintext, 2, n)
    return ciphertext


# using the private key, decrypts the encrypted message
# returns the decrypted message
def decrypt(encrypted_message, private_key: tuple) -> str:
    pass


# ensure plaintext is in a valid form
def is_plaintext(plaintext: str) -> bool:
    special_characters = "!@#$%^&*(){}:><?~/*-1234567890qwertyuiopasdfghjklzxcvbnm"
    valid = True
    for char in plaintext:
        if char in special_characters:
            valid = False
    return valid


# ensure ciphertext is in a valid form
def is_ciphertext(ciphertext: str) -> bool:
    pass


if __name__ == '__main__':
    my_dict = create_dict()
    public_key, private_key = create_keys()
    k, l = 2, 3
    message = input("")
    encrypted = encrypt(message, public_key)
    decrypted = decrypt(message, private_key)
    print("Original Message:", message)
    print("Encrypted Message:", encrypted)
    print("Decrypted Message:", decrypted)
    print("Public Key:", public_key)
    print("Private Key:", private_key)
