package edu.umb.cs.cs681.HW12;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.file.Path;
import java.util.HashMap;


public class AccessCounter 
{
	private HashMap<Path, Integer> accessCounter = new HashMap<Path, Integer>();
	private AccessCounter() 
	{
	};
	private static AccessCounter instance = null;
	private ReentrantLock lock = new ReentrantLock();
	private static ReentrantLock slock = new ReentrantLock();
	
	public static AccessCounter getInstance() 
	{
		slock.lock();
		try 
		{
			if (instance == null) 
			{
				instance = new AccessCounter();
			}
		} 
		finally 
		{
			slock.unlock();
		}
		return instance;
	}
	
	public void increment(Path pathName) 
	{
		lock.lock();
		try 
		{
			if (accessCounter.get(pathName) != null) 
			{
				accessCounter.put( pathName, getCount(pathName) + 1);
			}
			else 
			{
				accessCounter.put(pathName, 1);
			}
		} 
		finally 
		{
			lock.unlock();
		}
	}
	
	public int getCount(Path pathName) 
	{
		lock.lock();
		try 
		{
			
			if (accessCounter.get(pathName) != null) 
			{
				return accessCounter.get(pathName);
			}
			else 
			{
				return 0;
			}
		} 
		finally 
		{
			lock.unlock();
		}
	}
	
	public HashMap<Path, Integer> getAccessCounter() 
	{
	
		return accessCounter;
		
	}
	
}