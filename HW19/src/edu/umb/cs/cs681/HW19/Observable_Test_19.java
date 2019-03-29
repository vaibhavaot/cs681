package edu.umb.cs.cs681.HW19;

import java.util.ArrayList;

public class Observable_Test_19 
{

	public static void main(String[] args) throws InterruptedException 
	{

		Observable obs = new Observable();
		ArrayList<Thread> threads = new ArrayList<Thread>();

		 Thread t1 = new Thread(()-> {obs.Add_Observer((Observable o, Object obj)->{System.out.println("Thread 1 "+obj);});});
		 threads.add(t1);
		 t1.start();
		 Thread t2 = new Thread(()-> {obs.Add_Observer((Observable o, Object obj)->{System.out.println("Thread 2 "+obj);});});
		 threads.add(t2);
		 t2.start();
		 Thread t3 = new Thread(()-> {obs.Add_Observer((Observable o, Object obj)->{System.out.println("Thread 3 "+obj);});});
		 threads.add(t3);
		 t3.start();

		threads.forEach((Thread t) -> {
			try 
			{
				t.join();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		});
		
		obs.setChanged();
		obs.Notify_Observers("Hello Threads!");
	}
}