package edu.umb.cs.cs681.hw7;
import java.util.ArrayList;

public class PrimeNumberGenerator implements Runnable
{
	protected long from, to;
	protected ArrayList<Long> Prime_Number;

	public PrimeNumberGenerator(long from, long to)
	{
		if(from >= 1 && to >= from){
			this.Prime_Number = new ArrayList<Long>();
			this.from = from;
			this.to = to;
		} 
		else 
		{
			throw new RuntimeException("Wrong input values: from=" + from + " to=" + to);
		}
	}
	
	protected boolean Is_Prime(long number)
	{
		if(number == 1)
		{ 
			return false; 
		} 
		if( number > 2 && Is_Even(number) )
		{ 
		return false; 
		}
		long i;
        for (i = (long) Math.sqrt(number); number % i != 0 && i >= 1; i--){}
        if (i == 1)
        { 
        	return true; 
        }
        else
        {
         return false; 
         }
	}

	public ArrayList<Long> getPrimes()
	{ 
		return Prime_Number; 
	};
	
	protected boolean Is_Even(long number)
	{
		if(number % 2 != 0)
		{ 
			return false; 
		}
		else
		{ 
			return true; 
		}
	}
	public void run()
	{
		for (long number = from; number <= to; number++) 
		{
			if( Is_Prime(number) )
			{ 
				Prime_Number.add(number); 
			}
        }
	}
}