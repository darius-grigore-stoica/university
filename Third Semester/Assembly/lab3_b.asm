;Se da un sir de dublucuvinte. Sa se calculeze si sa se salveze in sirul D 
;toti octetii inferiori ai wordurilor inferioare care au ultima cifra divizibila cu 5.


ASSUME cs:text_,ds:data_

data_ SEGMENT
s dd 2, 10, 3
ls equ($ - s)
d db ls dup(0)
data_ ENDS

text_ SEGMENT
start:
mov ax, data_
mov ds, ax

mov si, 0; pargurgem s
mov di, 0;parcurgem d
mov cx, ls

repeta:
	mov al, byte ptr s[si]
	mov bx, 5
	cbw
	idiv bx
	
	cmp dx, 0
	je divizibil
	jne nedivizibil
	
	divizibil:
		mov d[di], al   
	add di, 1
	nedivizibil:
	add si, 4
loop repeta

mov ax, 4c00h
int 21h
text_ ENDS
END start