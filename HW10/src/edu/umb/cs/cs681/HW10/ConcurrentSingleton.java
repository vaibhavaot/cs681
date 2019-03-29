package edu.umb.cs.cs681.HW10;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton implements Runnable
{

    private ConcurrentSingleton(){}
        private static ReentrantLock lock = new ReentrantLock();
    private static boolean haveFirstInstance = false;
    private static ConcurrentSingleton instance = null;


    public static ConcurrentSingleton getInstance() throws InterruptedException 
    {
        try
        {
            if(instance==null)
            {
                if(!haveFirstInstance)
                {
                    haveFirstInstance = true;
                    Thread.sleep(10);
                }
                instance = new ConcurrentSingleton();
            }
            return instance;
        }
        finally
        {

        }
    }
    public void run()
    {
        try 
        {
            System.out.println(ConcurrentSingleton.getInstance());
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        ConcurrentSingleton s = new ConcurrentSingleton();
        for(int i=0;i<50;i++){
            Thread t = new Thread(s);
            t.start();
        }
    }
}

