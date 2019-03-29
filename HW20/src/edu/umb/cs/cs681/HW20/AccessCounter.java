package edu.umb.cs.cs681.HW20;

import java.nio.file.Path;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;

public class AccessCounter 
{
    private static final ReentrantLock objectCreationLock = new ReentrantLock();

    private static AccessCounter instance = null;
    private static final ConcurrentHashMap<Path, Integer> concurrentCounter = new ConcurrentHashMap<>();

    private AccessCounter() 
    {
    }

    public static AccessCounter getInstance() 
    {
        objectCreationLock.lock();
        try 
        {
            if (instance == null) 
            {
                instance = new AccessCounter();
            }
            return instance;
        } 
        finally 
        {
            objectCreationLock.unlock();
        }
    }

    public void increment(java.nio.file.Path path) 
    {
        concurrentCounter.compute(path, (p, i) -> i == null ? 1 : ++i);
    }

    public int getCount(java.nio.file.Path path) 
    {
        if(concurrentCounter.containsKey(path))
            return concurrentCounter.get(path);
        return 0;
    }
}
