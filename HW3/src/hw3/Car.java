package edu.umb.cs.cs681.hw3;

public class Car 
{
	private int price;
	private int year;
	private float milage;
	private int DominationCount;
	private String maker;

	public Car(int price, int year, float milage,String maker)
	{
		this.price = price;
		this.year = year;
		this.milage = milage;
		this.maker = maker;

	}
	
	public void setDominationCount(int dominationCount) 
	{
		DominationCount = dominationCount;
	}
	public int getDominationCount() 
	{
		return DominationCount;
	}
	public int getPrice()
	{
		return price;
	}
	public float getMilage()
	{
		return milage;
	}
	public String getMaker() 
	{
		return maker;
	}

	public int getYear()
	{
		return year;
	}
	


}
