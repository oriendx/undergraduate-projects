mydata	dcd		0xABCDEF01
res1		fill		4
		;		task 2.1
		;		Reverse the bits of mydata and save it to res1.
		;		The pattern is:
		;		The most significant bit will become the least significant bit.
		;		The second msb will become the second lsb.
		;		The third msb -> third lsb
		;		...
		;
		;		examples: 
		;		0x80008000 -> 0x00010001
		;		0x00018000 -> 0x00018000
		;		0x13700000 -> 0x00000EC8

		data	dcd		0xABCDEF01
res1		fill		4		
		adr		r0, data	
		ldr		r1, [r0]		
		
loop1
		cmp		r3, #31		;
		bgt		exit
		lsr		r2, r1, r3
		lsl		r2, r2, #31
		lsr		r2, r2, r3			
		add		r9, r9, r2	
		add		r3, r3, #1	
		b		loop1
exit
		adr		r9, res1		
		str		r0, [r9]		
