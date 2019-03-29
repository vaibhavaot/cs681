package edu.umb.cs.cs681.hw5;

import java.util.ArrayList;

public class PrimeTest {
	
	public static void main(String[] args) 
	{
		System.out.println("Prime numbers are Generating \n");
		
		PrimeNumberGenerator generator = new PrimeNumberGenerator(1L, 1000000L);
		
		Thread thread = new Thread(generator);
		try 
		{
		
			thread.start();
			
			thread.join();
			
		} 
		catch (InterruptedException e) 
		{
			System.out.println("Interrupted Exception  : "+e);
		}
		
		ArrayList<Long> primeList = new ArrayList<> ();
		generator.getPrimes().forEach(x -> primeList.add(x));
		System.out.println(" All Prime are puts in one list. \nSo, Total number of primes in the list is = " + primeList.size());
		
	}

}