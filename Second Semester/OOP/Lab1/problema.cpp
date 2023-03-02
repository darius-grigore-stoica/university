//
// Created by Darius Stoica on 3/1/2023.
//
int getNumberOfDigits(int number) {
    int lenght = 0;
    while(number){
        lenght++;
        number /= 10;
    }
    return lenght;
}

int findMinumimDigit(int number){
    int last_min = -1;
    int new_number = 0;
    int lenght = getNumberOfDigits(number);
    int steps = 0;
    while(steps < lenght) {
        int min = 10;
        int copy_number = number;
        while (copy_number) {
            int digit = copy_number % 10;
            if (digit < min and digit > last_min)
                min = digit;
            copy_number /= 10;
        }
        last_min = min;
        new_number = new_number * 10 + min;
        steps++;
    }
    return new_number;
}
