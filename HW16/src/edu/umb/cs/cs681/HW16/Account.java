package edu.umb.cs.cs681.HW16;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Account 
{
    private double balance = 0;
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition sufficientFundsCondition = reentrantLock.newCondition();
    private Condition belowUpperLimitFundsCondition = reentrantLock.newCondition();

    public void deposit(double amount) 
    {
        reentrantLock.lock();
        try 
        {
            System.out.println("Lock obtained by deposit");
            System.out.println("Current balance (d): " + balance);
            while (balance >= 300) 
            {
                try 
                {
                    belowUpperLimitFundsCondition.await();
                    System.out.print("W");
                    Thread.sleep(1000);
                } 
                catch (InterruptedException e) 
                {
                    System.out.println("Thread Interrupted "+e.getMessage());
                }
            }
            balance += amount;
            sufficientFundsCondition.signalAll();
            System.out.println(", New balance (d): " + balance);
        } 
        finally 
        {
            reentrantLock.unlock();
        }
    }

    public void withdraw(double amount) 
    {
        reentrantLock.lock();
        try 
        {
            System.out.println("Lock obtained by withdraw");
            System.out.println("Current balance (w): " + balance);
            while (balance <= 0) 
            {
                try 
                {
                	//System.out.println("2");
                    sufficientFundsCondition.await();
                    System.out.print("W");
                    Thread.sleep(1000);
                } 
                catch (InterruptedException e) 
                {
                	//System.out.println("1");
                    System.out.println("Thread Interrupted "+e.getMessage());
                }
            }
            balance -= amount;
            belowUpperLimitFundsCondition.signalAll();
            System.out.println(", New balance (w): " + balance);
        } 
        finally 
        {
            reentrantLock.unlock();
        }
    }
}
