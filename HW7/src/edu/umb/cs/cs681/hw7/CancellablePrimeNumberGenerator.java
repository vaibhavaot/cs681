package edu.umb.cs.cs681.hw7;
public class CancellablePrimeNumberGenerator extends PrimeNumberGenerator
{

	private boolean Done =! true;
	public void run()
	{
		for (long numbers = from; numbers <= to; numbers++) 
		{
			
			if( Is_Prime(numbers) )
			{ 
			this.Prime_Number.add(numbers); 
			}
			
			if(Done==true)
			{
				System.out.println("Stopped generating Prime_Number.");
				this.Prime_Number.clear();
				break;
			}
		}
	}
	public CancellablePrimeNumberGenerator(long from, long to) 
	{
		super(from, to);
	}
	public void Set_Done()
	{
		Done = true;
	}

}