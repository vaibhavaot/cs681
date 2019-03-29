package edu.umb.cs.cs681.HW9;
import java.util.concurrent.locks.ReentrantLock;

public class File {

    private boolean changed = false;
    private ReentrantLock lock = new ReentrantLock();
    
    public void save()
    {
       
            lock.lock();
            try
            {
                if(changed)
                {
                    System.out.println("Saving the file...");
                    changed = false;
                }
            }
            finally
            {
                lock.unlock();
            }
    }
    public void change()
    {

            lock.lock();
            try
            {
               System.out.println("Editing the file...");
               changed = true;
            }
            finally
            {
                lock.unlock();
            }
    }

    public static void main(String args[]) throws InterruptedException 
    {
        File file = new File();
        Editor e = new Editor(file);
        AutoSaver as = new AutoSaver(file);
        Thread editor = new Thread(e);
        Thread autoSave = new Thread(as);
        editor.start();
        Thread.sleep(10);
        autoSave.start();
        Thread.sleep(5000);
        e.setDone();
        as.setDone();
    }
}

class Editor implements Runnable
{
    private boolean done = false;
    File file;
    ReentrantLock lock = new ReentrantLock();

    Editor(File afile)
    {
        file = afile;
    }
    public void run()
    {
        while(true)
        {
            lock.lock();
            System.out.println("Edit change lock");
            
            try
            {
                if(done)
                {
                    System.out.println("Done Editing...");
                    break;
                }
                System.out.println("E");
                file.change();

                file.save();
                System.out.println("Edit Unlock");
                Thread.sleep(1000);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            } 
            finally
            {
                lock.unlock();
            }
        }
    }

    public void setDone()
    {
        done = true;
    }
}

class AutoSaver implements Runnable
{
    private boolean done = false;
    ReentrantLock lock = new ReentrantLock();
    File file;

    AutoSaver(File afile)
    {
        file = afile;
    }
    public void run()
    {
        while(true)
        {
            lock.lock();
            System.out.println("Auto Save, Save Lock");
            try
            {
                if(done)
                {
                    System.out.println("Done Saving...");
                    break;
                }
                System.out.println("AS");
                file.save();
                Thread.sleep(2000);
                System.out.println("Auto Save, Save Unlock");
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            } 
            finally
            {
                lock.unlock();
            }
        }
    }
    public void setDone()
    {
        done = true;
    }
}