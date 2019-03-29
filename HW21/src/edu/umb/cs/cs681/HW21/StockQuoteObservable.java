package edu.umb.cs.cs681.HW21;

import java.util.HashMap;

public class StockQuoteObservable extends Observable implements Observer
{
	private HashMap<String, Float> map= new HashMap<>();
	private ReentrantLock lockTQ=new ReentrantLock();
	
	public StockQuoteObservable() 
	{
		map = new HashMap<>();
	}
	public HashMap<String, Float> getMap() 
	{
		return map;
	}

	
	public void changeQuote(String t,float q) 
	{	lockTQ.lock();
		this.map.put(t,q);
		setChanged();
		notifyObservers(new StockEvent(t,q));
		lockTQ.unlock();
	}

}
