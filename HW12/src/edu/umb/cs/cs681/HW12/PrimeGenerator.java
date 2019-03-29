package edu.umb.cs.cs681.HW12;

import java.util.List;
import java.util.ArrayList;

public class PrimeGenerator 
{
    long from, to;
    List<Long> primeNumbers = new ArrayList<>();

    PrimeGenerator(long from, long to) 
    {
        if (from >= 1 && to >= from) 
        {
            this.from = from;
            this.to = to;
        } 
        else 
        {
            throw new RuntimeException("Wrong input: from = " + from + " to = " + to);
        }
    }

    boolean isPrime(long num) 
    {
        if (num == 0 || num == 1) return false;
        for (int i = 2; i <= num / 2; ++i) 
        {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public void generatePrimes() 
    {
        for (long n = from; n <= to; n++) 
        {
            if (isPrime(n)) 
            {
                primeNumbers.add(n);
            }
        }
    }
}
