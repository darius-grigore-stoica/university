def bubbleSort(arr, operator: str):
    operatori = operator.split()#a list with all the operators separated by whitespaces
    if(len(operatori) == 1):
        n = len(arr)
        swapped = False
        for i in range(n - 1):
             for j in range(0, n - i - 1):
                 if getattr(arr[j], operatori[0]) > getattr(arr[j + 1], operatori[0]):
                    swapped = True
                 arr[j], arr[j + 1] = arr[j + 1], arr[j]
        if not swapped:
            return
    if(len(operatori) == 2):
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
    if(len(operatori) == 3):
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