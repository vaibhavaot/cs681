package edu.umb.cs.cs681.HW12;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable
{
	private AccessCounter accessCounter = AccessCounter.getInstance();
	private ReentrantLock lock = new ReentrantLock();
	private volatile boolean done = false;
	
    static Path path1 = FileSystems.getDefault().getPath("src","homework18","file_root","a.html");
    static Path path2 = FileSystems.getDefault().getPath("src","homework18","file_root","b.html");
   	static Path path;
    public static void filesCreator() 
    {
		String content ="Hello world";
		 try 
		 {
			Files.write(path1, content.getBytes());
			Files.write(path2, content.getBytes());
			Files.write(path3, content.getBytes());
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
    }

	public static Path getRandom() 
	{
		Path [] array = new Path[] {path1, path2, path3};
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}

	public void setDone() 
	{
		done = true;
	}
	@Override
	public void run() 
	{
		while(true) 
		{
			if(done) 
			{
					System.out.println(Thread.currentThread() + "is done");
					break;
				}
				
					path = getRandom();
					accessCounter.increment(path); 
					accessCounter.getCount(path);
					try 
					{
							Thread.sleep(1000);
						} 
						catch (InterruptedException e) 
						{
							System.out.println(Thread.currentThread() +" is interrupted");
						} 

			}
	}
	

}