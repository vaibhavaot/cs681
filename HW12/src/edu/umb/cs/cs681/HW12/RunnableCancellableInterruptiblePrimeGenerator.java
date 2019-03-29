package edu.umb.cs.cs681.HW12;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeGenerator extends RunnableCancellablePrimeGenerator implements Runnable 
{
    private boolean done = false;
        

    private final ReentrantLock reentrantLock = new ReentrantLock();

    RunnableCancellableInterruptiblePrimeGenerator(long from, long to) 
    {
        super(from, to);
    }
    

    public void generatePrimes() 
    {
        System.out.println("Entry Point of Generate Prime");
        for (long n = from; n <= to; n++) 
        {
            reentrantLock.lock();
            try {
                if (Thread.interrupted() || done) 
                {
                    System.out.println("Stopped Generating Primes..... ");
                    this.primeNumbers.clear();
                    break;
                }
            } 
            finally 
            {
                reentrantLock.unlock();
            }

            if (isPrime(n)) 
            {
                this.primeNumbers.add(n);
            }
        }
        System.out.println("Exit Point of Generate Prime");
    }
	
    public ReentrantLock getLock() 
    {
        return reentrantLock;
    }

    @Override
    public void run() 
    {
        generatePrimes();
    }
}
