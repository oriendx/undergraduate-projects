numbers	dcd		12, 15
res		fill		4
		;		Task 3.1
		;		Implement positive number multiplication using the instructions you have learned.
		;		Assume the two numbers are 15 bits long at maximum and are stored in "numbers".
		;		Note that mul is not supported in VisUAL. Your solution must work for all possible values.
		;		Store the result in res.
		
	ENTRY
	    LDR     R3,=numbers
		LDR     R0,[R3]
		LDR     R1,[R3,#4]
		BL      IMUL
		LDR    R0,  =res	
		STR  R3,[R0]
		B      KK 
IMUL    MOV R3,#0
        ORR R2,R0,R1
		BEQ RETURN
		MOV R2,#0
K2		TST R1,#01
		BEQ  K1
		ADD R3,R3,R0
K1	    MOV R1,R1,LSR #1
	    MOV R0,R0,LSL #1
		ADD R2,R2,#1
        CMP R2,#15
		BNE K2
		
        
		
 RETURN  MOV PC,LR		

KK  B  KK		
		
		
		END






res2		fill		4
		;		Task 3.2
		;		Extend 3.1 to support all negative values with a 15-bit-long absolute values (its positive version being 15 bits long).
		;		Store the result in res2.

ENTRY
	    LDR     R3,=numbers
		LDR     R0,[R3]
		LDR     R1,[R3,#4]
		EORS R4,R1,R0
		MOVMI R4,#-1
	    MOVPL  R4,#0
		CMP R0,#0
		BGE K3
		MOV R2,R0
		BL NEG
		MOV R0,R2
K3      CMP R1,#0
        BGE K4
		MOV R2,R1
		BL NEG
		MOV R1,R2
K4      BL      IMUL
		CMP R4,#-1
		BNE K5
		MOV R2,R3
		BL NEG
		MOV R3,R2
		
		LDR    R0,  =res2	
		STR  R3,[R0]
		B      KK 
		
		
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
IMUL    MOV R3,#0
        ORR R2,R0,R1
		BEQ RETURN1
		MOV R2,#0
K2		TST R1,#01
		BEQ  K1
		ADD R3,R3,R0
K1	    MOV R1,R1,LSR #1
	    MOV R0,R0,LSL #1
		ADD R2,R2,#1
        CMP R2,#15
		BNE K2
		
        
RETURN1  MOV PC,LR	
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;
NEG   
RETURN2
     MVN R2,R2
	 ADD R2,R2,#1
     MOV PC,LR
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

KK  B  KK		
		
		
		END
