package edu.umb.cs.cs681.HW18;

public class SolutionWithApproach1 
{
    public void transfer(Account source, Account destination,
                         double amount)  
        {
        	source.getLock().lock();
        	destination.getLock().lock();
        	if (source.getBalance() < amount) 
        	{
            	throw new RuntimeException("Balance < transfer amount not possible");
        	}
        	source.withdraw(amount);
        	destination.deposit(amount);

        	destination.getLock().unlock();
        	source.getLock().unlock();
    	}
}
