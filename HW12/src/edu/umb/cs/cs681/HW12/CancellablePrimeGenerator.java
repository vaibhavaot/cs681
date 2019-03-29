package edu.umb.cs.cs681.HW12;

public class CancellablePrimeGenerator extends PrimeGenerator 
{
    private boolean done = false;

    CancellablePrimeGenerator(long from, long to) 
    {
        super(from, to);
    }

    public void setDone() 
    {
        done = true;
    }

    public void generatePrimes() 
    {
        for (long num = from; num <= to; num++) 
        {
            if (done)
            {
                System.out.println("Stopped Generating Primes..... ");
                this.primeNumbers.clear();
                break;
            }

            if (isPrime(num)) 
            {
                this.primeNumbers.add(num);
            }
        }
    }
}
