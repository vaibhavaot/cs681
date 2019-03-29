package edu.umb.cs.cs681.HW16;

public class WithdrawRunnable implements Runnable
{
    private Account account;
    WithdrawRunnable(Account account)
    {
        this.account=account;
    }

        private boolean done = false;

        public void run() 
        {
            while (!done) 
            {
                account.withdraw(100);
                try 
                {
                    Thread.sleep(2);
                } 
                catch (InterruptedException e) 
                {
                    System.out.println("Withdraw thread interrupted");
                }
            }
        }

        public void setDone() 
        {
            done = true;
        }
}
