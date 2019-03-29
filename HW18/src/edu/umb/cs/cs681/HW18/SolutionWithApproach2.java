package edu.umb.cs.cs681.HW18;

import java.util.Random;

public class SolutionWithApproach2 
{

    public void transfer(Account source, Account destination,
                         double amount)  
    {
        Random random = new Random();
        while(true)
        {
            if (source.getLock().tryLock()) 
            {
                try 
                {
                    if (destination.getLock().tryLock()) 
                    {
                        try 
                        {
                            if (source.getBalance() < amount) 
                            {
                                throw new RuntimeException("Balance < transfer amount not possible");
                            }
                            source.withdraw(amount);
                            destination.deposit(amount);
                            break;
                        } 
                        finally 
                        {
                            destination.getLock().unlock();
                        }
                    }
                } 
                finally 
                {
                    source.getLock().unlock();
                }
            }
            try 
            {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted "+e.getMessage());
            }
        }
    }

}
