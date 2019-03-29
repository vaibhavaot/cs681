package edu.umb.cs.cs681.HW21;

import java.util.LinkedList;

public abstract class Observable 
{
	private ReentrantLock lockObs=new ReentrantLock();
	private LinkedList<Observer> observers= new ConcurrentLinkedQueue<>();
	private boolean change = false;
	
	public void addObservers(Observer o) 
	{
		if (o == null)
			throw new NullPointerException("can't add null observer");
		observers.add(o);
	}
	public void deleteObserver(Observer o) 
	{
		observers.remove(o);
	}
	public Observable() 
	{
		
		observers = new LinkedList<Observer>();
	}
	public boolean hasChanged() 
	{
		return change;
	}
	public  void deleteObservers()
	{
	   observers.clear();
	 }	
	protected void setChanged() 
	{
		lockObs.lock();
		change = true;
		lockObs.unlock();
	}
	protected void clearChanged() 
	{
		lockObs.lock();
		change = false;
		lockObs.unlock();
	}
	public void notifyObservers() 
	{
		notifyObservers(null);
	}
	public  int countObservers()
	{
	     return observers.size();
	 }

	public void notifyObservers(Object arg) 
	{
		
		if(hasChanged())
		{
			for (int count = 0; count < observers.size(); count++)
			{ 		      
				
				System.out.println("\n");
		          observers.get(counter).update(this, arg);
		     }  
		}
		clearChanged();
	}
}
