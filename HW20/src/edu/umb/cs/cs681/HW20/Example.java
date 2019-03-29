package edu.umb.cs.cs681.HW20;

import java.util.ArrayList;
import java.util.List;

public class Example 
{

    public static void main(String[] args)
    {
        List<Thread> threads=new ArrayList<>();
        List<RequestHandler> rh=new ArrayList<>();
        for(int i = 0; i< 15; i++)
        {
            RequestHandler requestHandler=new RequestHandler();
            Thread thread = new Thread(requestHandler);
            threads.add(thread);
            rh.add(requestHandler);
            thread.start();
        }

        try 
        {
            Thread.sleep(5000);
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Thread interrupted "+e.getMessage());
        }

        for(int i = 0; i< 15; i++)
        {
            rh.get(i).getLock().lock();
            rh.get(i).setDone();
            rh.get(i).getLock().unlock();
        }

        for(int i = 0; i< 15; i++)
        {
            try 
            {
                threads.get(i).join();
            } 
            catch (InterruptedException e) 
            {
                System.out.println("Thread interrupted while joining "+e.getMessage());
            }
        }
    }


}
