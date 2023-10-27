; Sa se evalueze expresia z=(2+1/a)/(3+1/(b*b))-1/(c*c)

; Consideram b si c ca fiind byte

ASSUME cs:text_,ds:data_

data_ SEGMENT
a db 4
b db -7
c db 6
res dw ?
data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax
; Evaluarea propriu-zisa a expresiei

mov al, b
cbw
imul b; ax = b * b

mov bx, ax; bx = b * b
mov al, 1
cbw
cwd
idiv bx; dx:ax / bx = 1 /(b * b)

add ax, 3; ax = 3 + 1 / b * b

mov cx, ax; cx = 3 + 1/ b * b

mov bl, a
cbw

mov ax, 1
cbw
cwd
idiv bx; ax:dx / bx = 1 / a

add ax, 2; ax = 2 + 1 / a
cbw
cwd
idiv cx; ax = (2 + 1 / a) / (3 + 1 / b * b)

mov bx, ax;

mov al, c;
cbw
imul c; ax = c * c

mov cx, ax; cx = c * c
mov ax, 1
cbw
cwd
idiv cx; dx:ax / cx = 1 / c*c
mov res, bx
sub res, ax

; Terminarea programului
mov ax, 4c00h
int 21h
text_ ENDS

END start