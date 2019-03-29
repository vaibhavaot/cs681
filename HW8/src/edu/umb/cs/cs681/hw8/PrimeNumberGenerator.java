package edu.umb.cs.cs681.hw8;
import java.util.List;
import java.util.ArrayList;
public class PrimeNumberGenerator implements Runnable
{
	protected long from, to;
	protected List<Long> p;
	public PrimeNumberGenerator(long from, long to)
	{
		if(from >= 1 && to >= from)
		{
			this.p = new ArrayList<Long>();
			this.from = from;
			this.to = to;
		}
		else
		{
			throw new RuntimeException("From = " + from + "\n To=" + to +" This are Invalid Inputs \n ");
		}
	}
	public void run()
	{
		for (long numbers = from; numbers <= to; numbers++) 
		{
			if( Is_Prime(numbers) )
			{ 
				p.add(numbers); 
			}
        }
	}

	protected boolean Is_Prime(long numbers)
	{

		if(numbers == 1)
		{ 
			return false; 
		}
	
		if( numbers > 2 && Is_Even(numbers) )
		{ 
			return false; 
		}
		
		long extra1;

		for (extra1 = (long) Math.sqrt(numbers); numbers%extra1 != 0 && extra1 >= 1; extra1--){}


		if (extra1 == 1)
		{ 
			return true; 
		}
		else
		{ 
			return false; 
		}
	}
	public List<Long> Get_Primes(){ return p; };
	
	protected boolean Is_Even(long numbers)
	{
		if(numbers % 2 != 0)
		{ 
			return false; 
		}
		else
		{ 
			return true; 
		}
	}
	
}
