package edu.umb.cs.cs681.HW16;

public class Example 
{
    public static void main(String[] args) 
    {
        Account account = new Account();
        WithdrawRunnable withdrawRunnable = new WithdrawRunnable(account);
        DepositRunnable depositRunnable = new DepositRunnable(account);

        Thread withdrawThread = new Thread(withdrawRunnable);
        withdrawThread.start();
        try 
        {
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Thread Interrupted "+e.getMessage());
        }
        Thread depositThread = new Thread(depositRunnable);
        depositThread.start();

        withdrawThread.interrupt();
        depositThread.interrupt();

        try 
        {
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Thread Interrupted "+e.getMessage());
        }

        withdrawRunnable.setDone();
        depositRunnable.setDone();

        try 
        {
            withdrawThread.join();
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Thread Interrupted while joining threads "+e.getMessage());
        }
        try 
        {
            depositThread.join();
        } 
        catch (InterruptedException e) 
        {
            System.out.println("Thread Interrupted while joining threads "+e.getMessage());
        }
    }
}
