package edu.umb.cs.cs681.HW20;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import java.util.Random;

public class RequestHandler implements Runnable 
{
    private static final List<Path> RANDOM_FILE_PATHS = Arrays.asList(Paths.get("file1.html"),Paths.get("file2.html"));
    private final AccessCounter counter=AccessCounter.getInstance();
    private final ReentrantLock reentrantLock =new ReentrantLock();
    private boolean done = false;

    public ReentrantLock getLock() 
    {
        return reentrantLock;
    }
    
    public void setDone() 
    {
        reentrantLock.lock();
            this.done = true;
            reentrantLock.unlock();
    }



    @Override
    public void run() 
    {
        while (true) 
        {
            reentrantLock.lock();
            try 
            {
                if (done) 
                {
                    System.out.println("Stopped Process");
                    break;
                }
            }
            finally 
            {
                reentrantLock.unlock();
            }

            Path path = RANDOM_FILE_PATHS.get(Math.abs(new Random().nextInt())% RANDOM_FILE_PATHS.size());
            counter.increment(path);
            System.out.println(path + " = " + counter.getCount(path));

            try 
            {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e) 
            {
                System.out.println("Thread interrupted "+e.getMessage());
            }
        }
    }
}
