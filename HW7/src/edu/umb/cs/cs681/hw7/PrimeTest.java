package edu.umb.cs.cs681.hw7;

public class PrimeTest {
	
	public static void main(String[] args) {
		
		Thread thread1, thread3;
		Thread thread2, thread4;	
		CancellablePrimeNumberGenerator cancellableGen = new CancellablePrimeNumberGenerator(1L, 1000000L);
		thread1 = new Thread(cancellableGen);
		thread3 = new Thread(cancellableGen);
		thread1.start();
		thread3.start();
		System.out.println("Starting Cancellable_Prime_Number threads");
		cancellableGen.Set_Done();
		try {
			thread1.join();
			
			thread3.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println(cancellableGen.getPrimes().size() + " prime numbers generated.");
	    InterruptiblePrimeNumberGenerator interruptibleGen = new InterruptiblePrimeNumberGenerator(1000001L, 2000000L); 
	    thread2 = new Thread(interruptibleGen);
	    thread4 = new Thread(interruptibleGen);
	    thread2.start();
	    thread4.start();
	    System.out.println("\nStarting Interruptible_Prime_Number threads");
	    thread2.interrupt();
	    thread4.interrupt();
		try {
			thread2.join();
			thread4.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	
		
		System.out.println(interruptibleGen.getPrimes().size() + " prime numbers generated."); 
	
	}

}