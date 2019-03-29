package edu.umb.cs.cs681.HW19;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Observable 
{

	public ArrayList<Observer> Obs;
	private boolean Change;
	private ReentrantLock Lock;

	public Observable() 
	{

		Lock = new ReentrantLock();
		Change = false;
		Obs = new ArrayList<Observer>();

	}
	public void Notify_Observers(Object object) 
	{
		Object[] Arr_Local;
		Lock.lock();
		if (!hasChanged()) 
		{
			Lock.unlock();
			return;
		}
		Arr_Local = Obs.toArray();
		clearChanged();
		Lock.unlock();
		for (Object o : Arr_Local) 
		{
			((Observer) o).update(this, object);
		}
	}
	public void Notify_Observers() 
	{
		Notify_Observers(null);
	}

	public void Add_Observer(Observer obs) 
	{
		Lock.lock();
		try 
		{
		
			if (!this.Obs.contains(obs)) 
			{
				this.Obs.add(obs);
				System.out.println("add observer");
			}
		} 
		finally 
		{
			Lock.unlock();
		}
	}

	public void Delete_Observer(Observer obs) 
	{
		Lock.lock();
		try {
			if (Obs.contains(obs)) 
			{
				this.Obs.remove(obs);
				System.out.println(Obs.size());
			}
		} 
		finally 
		{
			Lock.unlock();
		}
	}



	public void setChanged() 
	{
		Lock.lock();
		this.Change = true;
		Lock.unlock();
	}

	public boolean hasChanged() 
	{
		return this.Change;
	}

	public void clearChanged() 
	{
		Lock.lock();
		this.Change = false;
		Lock.unlock();
	}





}