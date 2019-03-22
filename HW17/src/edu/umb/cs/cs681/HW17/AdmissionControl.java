package edu.umb.cs.cs681.HW17;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionControl
{
private static AtomicInteger currentVisitors =  new AtomicInteger(0);
private ReentrantLock lock = new ReentrantLock();

AdmissionControl()
{
	
}


public void enter()
{
	lock.lock();
	while(currentVisitors.intValue() >= 5)
	{
		System.out.print("Too many visitors. Please wait for a while! \n");
		
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
		
			e.printStackTrace();
		}
		}
	currentVisitors.incrementAndGet();
	lock.unlock();
	}


public void exit()
{

	lock.lock();
	while(currentVisitors.intValue() <= 5)
	{
		System.out.print("Few visitors. Please wait for a while! \n");
		
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
		
			e.printStackTrace();
		}
		}	
	currentVisitors.decrementAndGet(); 
	lock.unlock();	
}
public static void main(String[] arg) 
{
		EntranceHandler enterh = new EntranceHandler();
		ExitHandler  exith = new ExitHandler();
		MonitorHandler monitorh = new MonitorHandler();
}

public void countCurrentVisitors()
{
	int temp;
	lock.lock();
	temp = currentVisitors.intValue();
	System.out.println("count : " + temp);
	lock.unlock();
}

}

class EntranceHandler extends AdmissionControl implements Runnable
{
private AdmissionControl control = new AdmissionControl();

EntranceHandler()
{
	Thread t = new Thread(this);
	t.start();
}

public void run()
{
while(true)
{control.enter();
try 
{
	Thread.sleep(1000);
} 
catch (InterruptedException e) 
{
	e.printStackTrace();
}

}
} 
}



class ExitHandler implements Runnable{
	private AdmissionControl control = new AdmissionControl();

ExitHandler(){
	Thread t = new Thread(this);
	t.start();
}


public void run(){
	while(true) {
control.exit();
try {
	Thread.sleep(1000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
} 
}


class MonitorHandler implements Runnable{
	private AdmissionControl control = new AdmissionControl();

	MonitorHandler(){
		Thread t = new Thread(this);
		t.start();
	}
	
public void run(){
	while(true) {
control.countCurrentVisitors();
try {
	Thread.sleep(1000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
}
}