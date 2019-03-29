package edu.umb.cs.cs681.HW12;

import java.util.Arrays;
import java.util.List;

public class Example 
{
    public static void main(String[] args) 
    {
        RunnableCancellableInterruptiblePrimeGenerator generator1=new RunnableCancellableInterruptiblePrimeGenerator(1,1000);
        RunnableCancellableInterruptiblePrimeGenerator generator2=new RunnableCancellableInterruptiblePrimeGenerator(1001,2000);

        List<Thread> threadList=Arrays.asList(new Thread(generator1),new Thread(generator2));

        for (Thread th: threadList) 
        {
            th.start();
        }

        try 
        {
            Thread.sleep(1000);
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Thread interrupted "+e.getMessage());
        }

        generator1.setDone();
        generator2.getLock().lock();
        threadList.get(1).interrupt();
        generator2.getLock().unlock();

     
        for (Thread th: threadList) 
        {
            try 
            {
                th.join();
            } 
            catch (InterruptedException e) 
            {
                System.out.println("Thread interrupted while join "+e.getMessage());
            }
        }
    }
}
