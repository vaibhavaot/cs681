package edu.umb.cs.cs681.HW12;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class MCTest 
{
	public static void main(String[] args) throws InterruptedException 
	{
		AccessCounter accessCounter = AccessCounter.getInstance();
		HashMap<Path,Integer> hash = new HashMap<Path, Integer>();
		int NUM_THREADS = 15;
		RequestHandler[] req= new RequestHandler[NUM_THREADS];
		Thread[] threads = new Thread[NUM_THREADS];
		for (int j = 0; j < NUM_THREADS; j++) {
            req[j] = new RequestHandler();
			threads[j] = new Thread(req[j]);
          
        }

		  for (int i = 0; i < NUM_THREADS; i++) {
	            threads[i].start(); 
	   
	      }

		  for (int i = 0; i < NUM_THREADS; i++) {
			  	threads[i].interrupt();
			  	req[i].setDone();
			  	
		  }
	   
		 hash = accessCounter.getAccessCounter(); 
		 Thread.sleep(1000);
		 System.out.println("Access Counts : ");

		 for( Map.Entry<Path, Integer> entry : hash.entrySet()) {
			 System.out.println("[ " + entry.getKey().getFileName() + ", " + entry.getValue() + " ]");

		 }
		 
	}
}