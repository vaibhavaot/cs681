package edu.umb.cs.cs681.HW11;

import java.util.Arrays;
import java.util.List;

public class Example 
{
    public static void main(String[] args) throws InterruptedException 
    {
        RunnableInterruptiblePrimeGenerator generator1 = new RunnableInterruptiblePrimeGenerator(1, 1000);
        RunnableInterruptiblePrimeGenerator generator2 = new RunnableInterruptiblePrimeGenerator(1001, 2000);
        List<Thread> threadList= Arrays.asList(new Thread(generator1),new Thread(generator2));

        for (Thread th: threadList) 
        {
            th.start();
        }

        generator1.getLock().lock();
        threadList.get(0).interrupt();
        generator1.getLock().unlock();

        generator2.getLock().lock();
        threadList.get(1).interrupt();
        generator2.getLock().unlock();

        for (Thread th: threadList) 
        {
            th.join();
        }
    }
}
