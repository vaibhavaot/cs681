package edu.umb.cs.cs681.hw1;

import java.util.HashSet;
import java.util.Set;

public class DJIAQuoteObservable extends Observable 
{
	
	private float quote;
	private Set<Float> data = new HashSet<Float>();


	public void changeQuote(float q)
	{
		notifyObservers(new DJIAEvent(q));		
		this.quote = q;
		data.add(q);
		setChanged();
		
	}
		
	public Set<Float> getData() 
	{
		return data;
	}

}
