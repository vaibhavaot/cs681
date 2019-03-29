package edu.umb.cs.cs681.HW18;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Account 
{
    public double getBalance() 
    {
        return balance;
    }

    private double balance = 0;
    private ReentrantLock reentrantLock;
    private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;

    public Account()
    {
        reentrantLock = new ReentrantLock();
        sufficientFundsCondition = reentrantLock.newCondition();
        belowUpperLimitFundsCondition = reentrantLock.newCondition();
    }

    public ReentrantLock getLock() 
    {
        return reentrantLock;
    }

    public void withdraw(double amount)
    {
        reentrantLock.lock();
        try
        {
            System.out.println("Lock obtained ny withdraw");
            System.out.println(Thread.currentThread().getId() +
                    " (w): current balance: " + balance);
            while(balance <= 0)
            {
                System.out.println(Thread.currentThread().getId() +
                        " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getId() +
                    " (w): new balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        }
        catch (InterruptedException e)
        {
            System.out.println("Thread interrupted "+ e.getMessage());
        }
        finally
        {
            reentrantLock.unlock();
        }
    }
    
    public void deposit(double amount)
    {
        reentrantLock.lock();
        try
        {
            System.out.println("Lock obtained by deposit");
            System.out.println(Thread.currentThread().getId() +
                    " (d): current balance: " + balance);
            while(balance >= 300)
            {
                System.out.println(Thread.currentThread().getId() +
                        " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            }
            balance += amount;
            System.out.println(Thread.currentThread().getId() +
                    " (d): new balance: " + balance);
            sufficientFundsCondition.signalAll();
        }
        catch (InterruptedException e)
        {
            System.out.println("Thread interrupted "+ e.getMessage());
        }
        finally
        {
            reentrantLock.unlock();
            System.out.println("Lock released");
        }
    }

}
