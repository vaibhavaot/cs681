package edu.umb.cs.cs681.HW14;

import java.awt.Point;
import java.io.*;
import java.lang.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Customer implements Runnable
{
	private Address address; 
	private ReentrantLock lock = new ReentrantLock();

	public Customer(Address addr)
	{ 

		this.address = addr;
	
	} 

	public void setAddress(Address addr){
	
	lock.lock();
	try
	{
	this.address = addr;
	
	}
	finally 
	{
	lock.unlock();

	}	
}

public Address getAddress()
{ 
	lock.lock();
	try
	{
		return address;
	}
	finally 
	{
		lock.unlock();
	}	
}

@Override
public void run() 
{	
}


public static void main(String[] args)
{
Customer customer = new Customer( new Address("1 Southpoint drive","Boston","Massachusetts",02125));
customer.getAddress();
System.out.println(customer.getAddress());
customer.setAddress( new Address("30 harbor point","Boston","Massachusetts",02125) );
System.out.println(customer.getAddress());
customer.setAddress( customer.getAddress().change("15 Harbor point","Boston","Massachusetts",02125) );
System.out.println(customer.getAddress());

Thread t1=new Thread(()-> {customer.setAddress( new Address("1 north point drive","Boston","Massachusetts",02125) );
System.out.println(customer.getAddress());
});
Thread t2=new Thread(()-> {customer.setAddress( new Address("10 harbor point","Boston","Massachusetts",02125) );
System.out.println(customer.getAddress());
});

t1.start();
t2.start();


try {
	t1.join();
	t2.join();
} 
catch (InterruptedException e) 
{
	
	e.printStackTrace();
}
System.out.println(customer.getAddress().toString());
}
}