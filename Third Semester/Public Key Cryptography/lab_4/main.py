import random
from utils.chinese_remainder import chinese_remainder
from utils.tonelli_shanks import solve_squared_system_tonelli_shanks


def create_dict() -> dict[str, int]:
    """
    Create a dictionary storing key, values representing 26-letters alphabet and _ (blank)
    :return:
    """
    uppercase_letters = '_ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    my_dict = {}

    for index, letter in enumerate(uppercase_letters, start=0):
        my_dict[letter] = index

    return my_dict

def isPrime(x):
    if x == 1:
        return 0
    elif x == 2:
        return 1
    elif x % 2 == 0:
        return 0
    else:
        for i in range(3, x // 2, 2):
            if x % i == 0:
                return 0

def create_keys() -> (int, tuple):
    """
    Create private-key and public-key
    :return: public-key n, private-key (p, q)
    """
    # Generate two random integers
    p = random.randint(1, 100000)  # You can adjust the range as needed
    q = random.randint(1, 100000)
    while isPrime(p) == 0 and isPrime(q) == 0:
        p = random.randint(1, 100000)  # You can adjust the range as needed
        q = random.randint(1, 100000)

    # Compute their product
    n = p * q

    # Return the result
    return n, (p, q)


def get_key_by_value(dictionary, target_value):
    """
    Get the letter corresponding the value given as input
    :param dictionary:
    :param target_value:
    :return: corresponding key from the input dictionary
    """
    for key, value in dictionary.items():
        if value == target_value:
            return key
    # If the value is not found, returns an empty string
    return ""


def encrypt(message, public_key):
    """
    Encrypt the message using the Rabin Public-Key Crypto-system
    :param message:
    :param public_key:
    :return:
    """
    c = 0
    if is_plaintext(message):
        for m in message:
            c += pow(my_dict[m], 2, public_key) # c = m ^ 2 (mod n)
    return c


def split_string_into_blocks(input_string, block_size):
    """
    Get the list of blocks with length of block_size from splitting the input string
    :param input_string:
    :param block_size:
    :return: list of blocks
    """
    # Calculate the number of blocks
    num_blocks = (len(input_string) + block_size - 1) // block_size

    # Split the string into blocks
    blocks = [input_string[i * block_size:(i + 1) * block_size] for i in range(num_blocks)]

    # Add underscores to blocks that are shorter than block_size
    blocks = [block + '_' * (block_size - len(block)) for block in blocks]

    return blocks


def decrypt(encrypted_message, private_key: tuple, l) -> str:
    """
    Decrypt the message given as a parameter using the private-key
    :param encrypted_message:
    :param private_key:
    :param l:
    :return: the decrypted message
    """
    decrypted_message = ""
    for block in split_string_into_blocks(encrypted_message, l):
        # compute the numerical value of the message to decrypt
        numerical_equivalent = 0
        for i in range(0, l):
            numerical_equivalent += my_dict[block[i]] * 27 ** (2 - i)

        # solves the system of congruences
        # x^2 = a (mod p)
        # x^2 = b (mod q)
        congruences = [(numerical_equivalent % private_key[0], private_key[0]),
                       (numerical_equivalent % private_key[1], private_key[1])]
        system_solutions = solve_squared_system_tonelli_shanks(congruences)

        # solve the linear system of congruences
        # always 4 equations
        congruences_for_chinese = [(system_solutions[0][0], p), (system_solutions[0][1], p),
                                   (system_solutions[1][0], q), (system_solutions[1][1], q)]
        try:
            x = chinese_remainder(congruences_for_chinese)
            for numerical_value in x:
                # computes the k-sized blocks of letter from the solution of the system
                if numerical_value <= 27 ** 2:
                    first_letter = numerical_value // 27
                    second_letter = numerical_value - (first_letter * 27)
                    decrypted_message += get_key_by_value(my_dict, first_letter) + get_key_by_value(my_dict, second_letter) + " / "
        except ValueError as msg:
            print(msg)
    return decrypted_message


def is_plaintext(plaintext: str) -> bool:
    """
    Validation method to check for a message to be plaintext
    :param plaintext:
    :return: true if plaintext, false otherwise
    """
    if plaintext[0] == "_":
        return False
    valid_characters = "_ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return all(char in valid_characters for char in plaintext)


if __name__ == '__main__':
    # create the dictionary and private-key, public-key
    my_dict = create_dict()
    public_key, private_key = create_keys()
    p = private_key[0]
    q = private_key[1]
    n = p * q
    # p = 31
    # q = 53
    # private_key = (p, q)
    # public_key = p * q
    k, l = 2, 3
    message = "FOOTBALL"
    encrypted = encrypt(message, public_key)
    decrypted = decrypt("BED_HI", private_key, l)
    print("Original Message:", message)
    print("Encrypted Message:", encrypted)
    print("Decrypted Message:", decrypted)
    print("Public Key:", public_key)
    print("Private Key:", private_key)
