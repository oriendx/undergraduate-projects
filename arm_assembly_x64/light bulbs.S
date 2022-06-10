lbarray	fill		4
		;		task 2.2
		;		Assume that the first 10 bits of lbarray (light bulb array) controls 10 light bulbs.
		;		1 means that the light bulb is on. 0 means that the light bulb is off.
		;		Write a piece of code so that these light bulbs flash with the following pattern:
		;		1111100000
		;		0111110000
		;		0011111000
		;		0001111100
		;		0000111110
		;		0000011111
		;		0000111110
		;		0001111100
		;		.
		;		.

		;		That is, five consecutive lights bounces back and forth in the light bulb array.
		;		Also, assume that the value of "bounces" controls the number of time it bounces
		;		this part should finish when the number of bounces reaches the specified number.
		
		bounces	equ		5
lbarray	fill		4
		;		task 2.2
		adr		r0, lbarray
		mov		r1, #0b1111100000
		ldr		r2, =bounces		
loop1	mov		r3, #0			
loop2	lsr		r1, r1, #1		
		add		r3, r3, #1
		str		r1, [r0]			
		cmp		r3, #4
		bgt		exit1			
		b		loop2			
exit1
		mov		r3, #0			
loop3	lsl		r1, r1, #1		
		add		r3, r3, #1
		str		r1, [r0]			
		cmp		r3, #4
		bgt		exit2			
		b		loop3			
exit2
		add		r4, r4, #1		
		cmp		r4, r2
		bgt		exit3
		b		loop1			
exit3
