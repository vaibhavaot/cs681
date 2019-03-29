package edu.umb.cs.cs681.HW18;

public class ExampleWithSolution1 
{
    public static void main(String[] args) throws InterruptedException 
    {
    	System.out.println("ExampleWithSolution1 Started");
        SolutionWithApproach1 solution=new SolutionWithApproach1();
        Account source1=new Account();
        Account destination1=new Account();
        Thread thread1=new Thread(()->{
            source1.deposit(300);
            source1.withdraw(200);

        });
        Thread thread2=new Thread(()->{
            source1.deposit(150);
            solution.transfer(source1, destination1, 150);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Source : "+source1.getBalance());
        System.out.println("Destination :"+destination1.getBalance());
    }
}
