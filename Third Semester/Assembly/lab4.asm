;Dandu-se un sir de cuvinte in data segment sa se tipareasca pe ecran 
;suma octetilor superiori din cuvintele acestui sir.

assume cs:code,ds:data
data segment
	mesaj db 'Introduceti sirul: $'
	mesaj_out db 'Suma octetilor high din cuvintele sirului este: $'
	sir dw 10,?,10 dup(0)
	suma db 0
	newline db 10,13,'$'
	zero db '0'
data ends

code segment
start:
	mov ax, data
	mov ds, ax
	
	mov ah, 09h; afisam mesajul
	mov dx, offset mesaj
	int 21h
	
	mov ah, 0Ah; citim sirul de cuvinte pana la introducerea ENTER
	mov dx, offset sir
	int 21h
	
	mov ah, 09h; afisam newline
	mov dx, offset newline
	int 21h
	
	mov cl, byte ptr sir[1];in cx este lungimea sirului 
	mov si, 2
	
	repeta:
		mov bl, byte ptr sir[si]
		sub bl, zero
		add suma, bl
		add si, 1
	loop repeta
	
	mov ah, 09h
	mov dx, offset mesaj_out; afisam pe ecran mesajul 'Suma ... este'
	int 21h
	
	mov dl, suma
	mov ah, 02h; afisam pe ecran suma
	int 21h
	
	mov ah,09h
	mov dx,offset newLine
	int 21h
	
	mov ax,4C00h
	int 21h
code ends
end start