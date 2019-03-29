package edu.umb.cs.cs681.HW15;

import java.awt.Point;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Random;
import java.nio.file.Path;
import java.nio.file.Paths;


public class RequestHandler implements Runnable
{
	ArrayList<Path> filespath = new ArrayList<Path>();
	private boolean done = false;
	private final ReentrantLock lock = new ReentrantLock();
	
	RequestHandler(ArrayList<Path> filespath)
	{
		this.filespath=filespath;
	}
	public void setDone()
	{
		this.done=true; 
	}
	
	
	@Override
	public void run() 
	{
		AccessCounter ac = AccessCounter.getInstance();
		while(true) 
		{
			if(done)
			{
				System.out.println("Stopped the Thread");
				
				break; 
			}	
			int s=this.filespath.size();
			int random = (int )(Math.random() * s + 1);
		
			ac.increment(filespath.get(random-1));
			System.out.println(filespath.get(random-1)+" count ="+ac.getCount(filespath.get(random-1)));
		
			try 
			{
			Thread.sleep(3000);
		} 
			catch (InterruptedException e) 
			{
			continue;
		}
		}
	
	}
	
	
	
	public static void main(String[] args)
	{
		ArrayList<Path> filespath = new ArrayList<Path>();
		ArrayList<Thread> threads = new ArrayList<Thread>();

		File a = new File("./src/edu/umb/cs/cs681/hw15","a.html");
		File b = new File("./src/edu/umb/cs/cs681/hw15","b.html");
		Path p1 = Paths.get("./src/edu/umb/cs/cs681/hw15/b.html");
		Path p2 = Paths.get("./src/edu/umb/cs/cs681/hw15/a.html");
		filespath.add(p1);
		filespath.add(p2);

		final int nThread = 11;
	 	RequestHandler rh= new RequestHandler(filespath);
		  for (int i = 0; i < nThread; i++) 
		  {
				Thread te = new Thread(rh);
				
		      	threads.add(te);
		      	te.start();
			
		  	
		    
		    }
		try 
		{
	Thread.sleep(3000);
} 
		catch (InterruptedException e1) 
		{

			e1.printStackTrace();
		}
		    for (Thread t: threads) 
		    {
		      try 
		      {
		    	  t.interrupt();   
			      rh.setDone();
					t.join();
				} 
				catch (InterruptedException e) 
				{
				
				e.printStackTrace();
				}
		    }
		   
	  }
	}
