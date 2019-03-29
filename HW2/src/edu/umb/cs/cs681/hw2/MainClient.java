package edu.umb.cs.cs681.hw2;

public class MainClient 
{


	public static void main(String[] args) 
	{
		 RemoteControl control = new RemoteControl();
		    Light light = new Light();
		    
		     Command lightsOff = () -> 
		    {
		    	 light.switchOff();
		    };
		    Command lightsOn = () -> 
		    {
		    	 light.switchOn();
		    };
		   
		    
		    //switch on the light 
		    control.setCommand(lightsOn);
		    control.pressButton();
		    
		    //switch off the light 
		    control.setCommand(lightsOff);
		    control.pressButton();
	}

}
