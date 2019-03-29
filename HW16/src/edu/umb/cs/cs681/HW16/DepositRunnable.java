package edu.umb.cs.cs681.HW16;

public class DepositRunnable implements Runnable 
{
    private boolean done = false;
    private Account account;
    DepositRunnable(Account account)
    {
        this.account=account;
    }

    public void run() 
    {
        while (!done) 
        {
            account.deposit(100);
            try 
            {
                Thread.sleep(500);
            } 
            catch (InterruptedException e) 
            {
                System.out.println("Deposit thread interrupted");
            }
        }
    }

    public void setDone() 
    {
        done = true;
    }
}
