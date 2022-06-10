#include<reg51.h> 
//access the bit addressable memory 
//low electrical level(0) indicated key was triggered
sbit ag = P0 ^ 3;//declare r1 for and gate
sbit k1 = P0 ^ 6;//declare c1 for key 1
sbit k2 = P0 ^ 5;
//an electrical level equal to corresponded port indicated the led was lited, high indiates stop, low indicates running		
sbit m1 = P3 ^ 0;//declare the pin for motor anti-clockwise
sbit m2 = P3 ^ 1;//declare the pin for motor clockwise

void main()
{
	ag=0;//turn the and gate off
	while(1)
	{
		if(k1==0)//if key 1 is pressed first
		{
		  m2 = 0;//motor starts running clockwise		 
			while(!k1){
				if(!k2){//if key 2 is pressed while key 1 is being pressed
				  m1 = 0;//motor stops
			   }else{
				   m1 = 1;
			   }

			};//else motor remain stops
		   m1 = 1;
		   m2 = 1;
	   		
		}	
		else if(k2==0)//if key 2 is pressed first
		{
				
			m1 = 0;//motor starts running anti-clockwise
		
		  while(!k2){
			  if(!k1){//if key 1 is pressed while key 2 is being pressed
				m2 = 0;//motor stops 
			  }else{
				  m2 = 1; 
			  }
		  };//else motor remian stops
		  	m1 = 1;
        m2 = 1;	
		}						   
	}
}
