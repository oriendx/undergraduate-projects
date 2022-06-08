lbarray	fill		4
arrayLen	equ		3
		;		task 2.3
		;		Write an updated version of 2.2 that allows you to specify
		;		the length of light using "arraylen".
		;		e.g. if arraylen is 5, the will behave exactly like 2.2
		;		if arraylen is 3:
		;		1110000000
		;		0111000000
		;		0011100000
		;		...

		arrayLen	equ		3
res3		fill		4
		;		task 2.3
bounces	equ		5
		ldr		r3, =bounces		
		adr		r0, res3			
ref		equ		0b1111111111
		ldr		r1, =arrayLen		
		ldr		r2, =ref
		lsl		r2, r2, r1
		lsr		r2, r2, r1		
		rsb		r4, r1, #9		
loop1	mov		r5, #0
loop2	lsr		r2, r2, #1
		add		r5, r5, #1
		str		r2, [r0]			
		cmp		r5, r4
		bgt		exit1			
		b		loop2			
exit1
		mov		r5, #0
loop3	lsl		r2, r2, #1
		add		r5, r5, #1
		str		r2, [r0]			
		cmp		r5, r4
		bgt		exit2			
		b		loop3			
exit2
		add		r6, r6, #1		
		cmp		r6, r3
		bgt		exit3
		b		loop1			
exit3
