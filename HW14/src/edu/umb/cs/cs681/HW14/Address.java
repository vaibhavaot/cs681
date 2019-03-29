
package edu.umb.cs.cs681.HW14;

public final class Address
{
	private final String street,city,state;
	private final int zipcode;
	
	Address(String a,String b,String c,int z)
	{
		this.street=a;
		this.city=b;
		this.state=c;
		this.zipcode=z;
	}
	
	
	public String toString()
	{
		return street + "/" + city + "/" + state  + "/" +zipcode; 
	}
	
	public Address change(String street, String city, String state, int zipcode)
	{
		return new Address(street, city, state, zipcode); 
	}
	
	public Boolean equals(Address a)
	{
		if( this.toString().equals(a.toString()) )
		{ 
			return true; 
		} 
		else
		{ 
			return false; 
		}
	}	
}
