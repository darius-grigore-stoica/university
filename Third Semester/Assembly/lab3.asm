;12. Se dau 2 siruri de octeti A si B. 
;Sa se construiasca sirul R care sa contina doar elementele pare si negative din cele 2 siruri. 

ASSUME cs:text_,ds:data_

data_ SEGMENT
a db 2, 1
la equ($-a)
b db 5, -5, 7, -6
lb equ($-b)
r db la+lb dup(0)
data_ ENDS

text_ SEGMENT
start:
mov ax, data_
mov ds, ax

mov si, 0;a
mov di, 0;b
mov bp, 0;r
mov cx, la

again:
	mov dl, a[si]
	mov al, a[si];salvam in ax elementul din a de pe si
	cmp al,0
	JLE	negativ;in cazul in care e negativ
	JG pozitiv
	
	negativ:
		cbw
		mov bl, 2
		idiv bl;impartim numarul la 2
		cmp ah, 0;iar daca restul e 0
		JE par;atunci numarul este pas
		JNE impar
		par:
			mov r[bp], dl;si in salvam in sirului rezultat
			add bp, 1
		impar:
	pozitiv:
	add si, 1
loop again

mov ax, 0
mov cx, lb

again_b:
	mov dl, b[di]
	mov al, b[di];salvam in ax elementul din a de pe si
	cmp al,0
	JLE	negativ_b;in cazul in care e negativ
	JG pozitiv_b
	
	negativ_b:
		cbw
		mov bl, 2
		idiv bl;impartim numarul la 2
		cmp ah, 0;iar daca restul e 0
		JE par_b;atunci numarul este pas
		JNE impar_b
		par_b:
			mov r[bp], dl;si in salvam in sirului rezultat
			add bp, 1
		impar_b:
	pozitiv_b:
	add di, 1
loop again_b

mov ax, 4c00h
int 21h
text_ ENDS
END start