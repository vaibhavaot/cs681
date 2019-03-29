package edu.umb.cs.cs681.hw2;


public class Light
{
private boolean on;

public void switchOff()
{
	System.out.println("switch off the Lights ");
    on = false;
}

public void switchOn()
{
	System.out.println("switch on the lights ");
    on = true;
}
}