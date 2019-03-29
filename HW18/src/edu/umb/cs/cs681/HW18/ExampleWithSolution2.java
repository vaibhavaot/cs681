package edu.umb.cs.cs681.HW18;

public class ExampleWithSolution2 
{
    public static void main(String[] args) throws InterruptedException 
    {
    	System.out.println("ExampleWithSolution2Started");
        SolutionWithApproach2 solution=new SolutionWithApproach2();
        Account source=new Account();
        Account destination=new Account();
        Thread thread1=new Thread(()->{
            source.deposit(300);
            source.withdraw(100);

        });
        Thread thread2=new Thread(()-> {
            source.deposit(50);
            solution.transfer(source, destination, 150);
        }
        );
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Source = "+source.getBalance());
        System.out.println("Destination = "+destination.getBalance());
    }
}
