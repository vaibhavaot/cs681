package edu.umb.cs.cs681.hw8;
import java.util.ArrayList;
public class PrimeTest 
{
			
	public static void main(String[] args) throws Exception 
	{
		ArrayList<Thread> threads = new ArrayList<Thread>();
				
				CancellablePrimeNumberGenerator Can_Prime_Num_Gen = new CancellablePrimeNumberGenerator(1L, 500L);
				
				// <------ Creating 5 Threads ------------>//
				
			    final int Number_Thread = 5;
			    for (int extra1 = 0; extra1 < Number_Thread; extra1++) 
			    {
			        Thread thread = new Thread(Can_Prime_Num_Gen);
			        threads.add(thread);
			        thread.start();
			      } 
			      threads.forEach(
			    		  (Thread thread)->{
			    			  try {
			    			  		thread.join();
			    			  } catch (Exception e) {
			    				  	e.printStackTrace();}
			    		  }
			      ); 
			      System.out.println("\n\nTotal of " + Can_Prime_Num_Gen.Get_Primes().size() + " prime numbers were found.\n \n Number Of Threads are " + 
			    	       + Number_Thread );
			      
			    
			   
			}

		


	}