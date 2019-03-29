package edu.umb.cs.cs681.hw8;
import java.util.concurrent.locks.ReentrantLock;
	public class CancellablePrimeNumberGenerator extends PrimeNumberGenerator
	{
		int counting  = 1;
		private boolean done = false;
		ReentrantLock lock = null;

		public CancellablePrimeNumberGenerator(long from, long to) 
		{
			super(from, to);
			lock = new ReentrantLock();
		}

		public void run()
		{
			System.out.println("\n\nThreads are generating Prime Numbers - -"+counting++ );
			for (long n = from; n <= to; n++) 
			{
				
				try 
				{
					lock.lock();
					
					if( Is_Prime(n) )
					{ 
						 
						this.p.add(n); 
						
						System.out.print(n+", ");
					} 
					
					if(done==true)
					{
						
						System.out.println("Stopped generating prime numbers- -");
						this.p.clear();
						break; 
					}
					
				} 
				finally 
				{
					lock.unlock();
				}
				
			}
		}

		public void setDone()
		{
			lock.lock();
			
			try
			{
				done = true;
			} 
			finally 
			{
				lock.unlock();
			}
		}
		


	}
