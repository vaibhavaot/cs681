package edu.umb.cs.cs681.hw1;

import java.util.HashMap;

public class StockQuoteObservable extends Observable 
{
	private HashMap<String, Float> map;
	
	
	public StockQuoteObservable() 
	{
		map = new HashMap<>();
	}
	public HashMap<String, Float> getMap() 
	{
		return map;
	}

	
	public void changeQuote(String t,float q) 
	{
		this.map.put(t,q);
		setChanged();
		notifyObservers(new StockEvent(t,q));
	}

}
