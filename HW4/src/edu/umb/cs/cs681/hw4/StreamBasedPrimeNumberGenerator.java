package edu.umb.cs.cs681.hw4;

import java.util.ArrayList;
import java.util.stream.LongStream;

public class StreamBasedPrimeNumberGenerator extends PrimeNumberGenerator 
{

	public StreamBasedPrimeNumberGenerator(long from, long to) 
	{
		super(from, to);
	}

@SuppressWarnings("unchecked")
	public void run()
	{
		this.p = (ArrayList<Long>) LongStream.rangeClosed(this.from, this.to)
	            .filter(x -> Is_Prime(x));
	}
}