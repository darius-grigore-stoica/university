;Se da cuvântul A. Sa se obtina în B cuvântul obtinut prin rotirea spre stânga a lui A 
;cu atâtea pozitii cât reprezinta numarul binar cuprins între bitii 12-15 ai lui A.

ASSUME cs:text_,ds:data_

data_ SEGMENT
a dw 001010011000001b
b dw ?
data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax
; Rezolvarea propriu zisa

mov bx, a
mov ax, a;izolaram bitiilor 12-15 din a
and ax, 111100000000000b
shr ax, 11;

mov cl, al
rol bx, cl

mov b, bx


; Terminarea programului
mov ax, 4c00h
int 21h
text_ ENDS

END start