package edu.umb.cs.cs681.hw1;

public class DJIAEvent 
{
	private float djia;
	
	public void setDjia(float djia) 
	{
		this.djia = djia;
	}
	public float getDjia() 
	{
		return djia;
	}

	public DJIAEvent(float djia)
	{
		this.djia = djia;
	}

}
