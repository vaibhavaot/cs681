package edu.umb.cs.cs681.hw7;

public class InterruptiblePrimeNumberGenerator extends PrimeNumberGenerator
{
	public InterruptiblePrimeNumberGenerator(long from, long to) 
	{
		super(from, to);
	}
			
	public void run()
	{
		for (long numbers = from; numbers <= to; numbers++) 
		{
			if( Is_Prime(numbers) )
			{ 
			this.Prime_Number.add(numbers); 
			}
			
			if(Thread.interrupted()==true)
			{
				System.out.println("Stopped generating Prime_Numbers.");
				this.Prime_Number.clear();
				break;
			}
		}
	}	
}
