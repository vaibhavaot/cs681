package edu.umb.cs.cs681.hw6;

import java.util.ArrayList;

public class MCTest 
{
	public static void main (String[] args)
	{
		long starting = System.currentTimeMillis();
	    ArrayList<Thread> threads = new ArrayList<Thread>();
	    System.out.println("Wait For While .....\n");
	    
	    ArrayList<String> Number_Threads = new ArrayList<String>();
	    Number_Threads.add("1");  
	    Number_Threads.add("2");
	    Number_Threads.add("4"); 
	    Number_Threads.add("8");  
	    
	    for(int extra1 = 0; extra1< Number_Threads.size(); extra1++)
	    {
	    
		    final long Time  = Long.parseLong("10000000000");
		   
		    final int Number_Thread = Integer.parseInt(Number_Threads.get(extra1));
		
		    System.out.println("\nProcess "+ Number_Threads.get(extra1) + "Please wait It's in Process ... \n");
		    
		    for (int extra2 = 0; extra2 < Number_Thread; extra2++) 
		    {
		      Thread thread = new Thread(
		    		  
		    		 () -> { 
		    			 	
		    			 	int number = 25;
		    		  		for (long extra3 = 0; extra3 < Time; extra3++) {number *= 25;};
		    		  		System.out.println(Thread.currentThread().getName() + ", calculations made is : " + number+" \n");
		    		  })
		    		  ;
		      threads.add(thread);
		      thread.start();
		    }
		    
		    threads.forEach(
		    		(Thread thread)->
		    		{
		    			try 
		    			{
		    				thread.join();
		    			} 
		    			catch (Exception e) 
		    			{
		    				e.printStackTrace();
		    			}
		    			});
	    }
	    
	    long time_Millis = System.currentTimeMillis() - starting;
	    
	    System.out.println("\nTotal time taken by threads is:" +time_Millis/1000+ " seconds" );
	    
	    
  }

}