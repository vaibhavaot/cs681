package edu.umb.cs.cs681.HW11;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableInterruptiblePrimeGenerator extends InterruptiblePrimeGenerator implements Runnable 
{
    private final ReentrantLock reentrantLock = new ReentrantLock();

    RunnableInterruptiblePrimeGenerator(long from, long to) 
    {
        super(from, to);
    }

    public ReentrantLock getLock() 
    {
    	//system.out.println("check");
        return reentrantLock;
    }

    public void run() 
    {	
    	//system.out.println("i am here");
        generatePrimes();
    }

    public void generatePrimes() 
    {
        System.out.println("Entry Point of Generate Prime.");

        for (long num = from; num <= to; num++) 
        {	
        	//System.out.println(" check ");
            reentrantLock.lock();

            if (Thread.interrupted()) 
            {
                System.out.println("Stopped Generating Primes.");
                this.primeNumbers.clear();
                break;
            }

            reentrantLock.unlock();

            if (isPrime(num)) 
            {
                this.primeNumbers.add(num);
            }
        }

        System.out.println("Exit Point of Generate Prime.");
    }


}