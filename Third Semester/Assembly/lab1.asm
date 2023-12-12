; Sa se evalueze expresia a*b+c

; am considerat a si b ca fiind octeti iar c cuvant, rezultatul tinandu-l in rez , si acesta cuvant.

ASSUME cs:text_,ds:data_

data_ SEGMENT
a db 4
b db 4
c dw 123
rez dw ?
data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax
; Evaluarea propriu-zisa a expresiei
mov al, a ; al = a
mul b ; ax = a*b
add ax, c ; ax = a*b+c
mov rez, ax ; rez= a*b+c
; Terminarea programului
mov ax, 4c00h
int 21h
text_ ENDS

END start

; cazuri de utilizare : a=3,b=5,c=12-->rez=27