
#Prin operator vom întelege atributul după care dorim a se executa compararea a doua obiecte de tip Car
#Un operator poate fi "token", de tip string, care se va compara lexicografic

def bubbleSort(arr, operator: str):
    """
    Algoritm de sortare Bubble Sort. Complexitate in timp: O(n^2), n - numarul de elemente din lista arr
    :param arr: o lista de elemente ce dorim a fi sortate
    :param operator: o lista de operatori dupa care dorim a face sortarea
    :return:
    """
    operatori = operator.split()
    #operatori este o lista care cuprinde, pe fiecare pozitie, cate un operator
    #dat in parametrul de intrare operator
    if (len(operatori) == 1):
        n = len(arr)
        swapped = False
        for i in range(n - 1):
            for j in range(0, n - i - 1):
                if getattr(arr[j], operatori[0]) > getattr(arr[j + 1], operatori[0]):
                    swapped = True
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
        if not swapped:
            return
    if (len(operatori) == 2):
        n = len(arr)
        swapped = False
        for i in range(n - 1):
            for j in range(0, n - i - 1):
                if getattr(arr[j], operatori[0]) > getattr(arr[j + 1], operatori[0]):
                    swapped = True
                    arr[j], arr[j + 1] = arr[j + 1], arr[j]
                if getattr(arr[j], operatori[0]) == getattr(arr[j + 1], operatori[0]):
                    if getattr(arr[j], operatori[1]) > getattr(arr[j + 1], operatori[1]):
                        swapped = True
                        arr[j], arr[j + 1] = arr[j + 1], arr[j]
        if not swapped:
            return

    if (len(operatori) == 3):
        n = len(arr)
        swapped = False
        for i in range(n - 1):
            for j in range(0, n - i - 1):
                if getattr(arr[j], operatori[0]) > getattr(arr[j + 1], operatori[0]):
                    swapped = True
                    arr[j], arr[j + 1] = arr[j + 1], arr[j]
                elif getattr(arr[j], operatori[0]) == getattr(arr[j + 1], operatori[0]):
                    if getattr(arr[j], operatori[1]) > getattr(arr[j + 1], operatori[1]):
                        swapped = True
                        arr[j], arr[j + 1] = arr[j + 1], arr[j]
                elif getattr(arr[j], operatori[1]) == getattr(arr[j + 1], operatori[1]):
                    if getattr(arr[j], operatori[2]) > getattr(arr[j + 1], operatori[2]):
                        swapped = True
                        arr[j], arr[j + 1] = arr[j + 1], arr[j]
        if not swapped:
            return
def partition(array, low, high, operator: list[str]):
    """
    Functie de partitionare, parte a algoritmului de QuickSort
    :param low: capatul din stanga al segmentului din lista array
    :param high: capatul din dreapta al segmentului din lista array
    :param operator: lista de tip string a operatorilor dupa care dorim sortarea
    :return:
    """
    i = low - 1
    for j in range(low, high - 2):
        if(len(operator) == 1):
            if getattr(array[j], operator[0]) < getattr(array[high], operator[0]):
                i = i + 1
                (array[i], array[j]) = (array[j], array[i])
        if(len(operator) == 2):
            if getattr(array[j], operator[0]) == getattr(array[high], operator[0]):
                if getattr(array[j], operator[1]) < getattr(array[high], operator[1]):
                    i = i + 1
                    (array[i], array[j]) = (array[j], array[i])
        if(len(operator) == 3):
            if getattr(array[j], operator[1]) == getattr(array[high], operator[1]) and operator[2] != "":
                if getattr(array[j], operator[2]) < getattr(array[high], operator[2]):
                    i = i + 1
                    (array[i], array[j]) = (array[j], array[i])
    (array[i + 1], array[high]) = (array[high], array[i + 1])
    return i + 1


def quickSort(array, low, high, operator: str):
    """
    Algoritm de sortare QuickSort. Complexitatea in timp: O(n * log n),
    n - numarul de elemente din lista array,
    log n - functia logaritm in baza 2 aplicată numarului natural n
    :param array: o lista ce dorim a fi sortata
    :param low: capatul din stanga al segmentului din lista array
    :param high: capatul din dreapta al segmentului din lista array
    :param operator: lista de tip string a operatorilor dupa care dorim sortarea
    :return:
    """
    operatori = operator.split()
    if low < high:
        pi = partition(array, low, high, operatori)
        quickSort(array, low, pi - 1, operator)
        quickSort(array, pi + 1, high, operator)

