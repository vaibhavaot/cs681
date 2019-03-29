package edu.umb.cs.cs681.hw4;
import java.util.ArrayList;

public class PrimeTest {
		
		public static void main(String[] args) 
		{
			System.out.println("Stream Prime number Generater.");
			
			PrimeNumberGenerator generator1 = new PrimeNumberGenerator(1L, 1000000L);
			Thread t1 = new Thread(generator1);
			
			PrimeNumberGenerator generator2 = new PrimeNumberGenerator(1000001L, 2000000L);
			Thread t2 = new Thread (generator2);
			
			try {
				System.out.println("\nGenerating  Stream Prime numbers from 1 to 1000000 .\n ");
				t1.start(); 
				
				System.out.println(" \nGenerating Stream Primes from from 1000001 to 2000000 .\n");
				t2.start();
			
			t1.join();
			
			t2.join();

		} catch (InterruptedException e) {
			System.out.println("Interrupted Exception: " + e);
		}

		ArrayList<Long> list = new ArrayList<>();
		generator1.getPrimes().forEach(x -> list.add(x));
		generator2.getPrimes().forEach(x -> list.add(x));

			System.out.println(" All Prime are puts in one list. So, Total number of primes in the list is :" + list.size());

			//System.out.println("\n"+list +"\n");

			System.out.println("\n First prime number is: ");
			
			list.stream()
	        .filter(x -> x.equals(2L))
	        .forEach(System.out::print);
			System.out.println("\n Last prime number is: ");
			
			list.stream()
	        .filter(x -> x.equals(1999993L))
	        .forEach(System.out::print);

		}


}
