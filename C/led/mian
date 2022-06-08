#include<reg51.h> 
//access the bit addressable memory 
//low electrical level(0) indicated key was triggered
sbit ag = P0 ^ 3;//declare r1 for and gate
sbit k1 = P0 ^ 6;//declare c1 for key 1
sbit k2 = P0 ^ 5;
//an electrical level equal to corresponded port indicated the led was lited		
sbit LED0 = P1 ^ 0;//declare the pin for led0
sbit LED1 = P1 ^ 1;
sbit LED2 = P1 ^ 2;
sbit LED3 = P1 ^ 3;
sbit LED4 = P1 ^ 4;
sbit LED5 = P1 ^ 5;
sbit LED6 = P1 ^ 6;
sbit LED7 = P1 ^ 7;

void main()
{
	ag=0;//turn the and gate off
	while (1)//the loop goes on forever till a break is issued explicitly
	{
		if(k1==0)//if key 1 is pressed first
		{
			LED0 = 0;
			LED1 = 0;
			LED2 = 0;
			LED3 = 0;//led 0-3 are litted
		 
			while(!k1){
				if(!k2){//if key 2 is pressed while key 1 is being pressed
				  LED4 = 0;
				  LED5 = 0;
				  LED6 = 0;
				  LED7 = 0; //led 4-7 are litted
			   }else{//if not, led 4-7 remain extinct
				   LED4 = 1;
				   LED5 = 1;
				   LED6 = 1;
				   LED7 = 1;
			   }

			};//else lights remian off 
			LED0 = 1;
			LED1 = 1;
			LED2 = 1;
			LED3 = 1;	
			LED4 = 1;
			LED5 = 1;
			LED6 = 1;
			LED7 = 1;
	   		
		}	
		else if(k2==0)//if key 2 is pressed first
		{
				
			LED4 = 0;
			LED5 = 0;
			LED6 = 0;
			LED7 = 0;//led 4-7 are litted
		
		  while(!k2){
			  if(!k1){//if key 1 is pressed while key 2 is being pressed
				LED0 = 0;
				LED1 = 0;
				LED2 = 0;
				LED3 = 0;//led 0-3 are litted  
			  }
			  else{
				  LED0 = 1;
				  LED1 = 1;
				  LED2 = 1;
				  LED3 = 1; 
			  }
		  };//else lights remain off 
		  	LED0 = 1;
			LED1 = 1;
			LED2 = 1;
			LED3 = 1;
			LED4 = 1;
			LED5 = 1;
			LED6 = 1;
			LED7 = 1;	
		}						   
	}
	
}

