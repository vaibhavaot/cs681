package edu.umb.cs.cs681.HW21;

public class StockEvent 
{
	private float quote;
	private String ticker;
	
	public String getTicker() 
	{
		return ticker;
	}
	public void setTicker(String ticker) 
	{
		this.ticker = ticker;
	}
	public void setQuote(float quote) 
	{
		this.quote = quote;
	}
	public float getQuote() 
	{
		return quote;
	}	
	public StockEvent(String ticker, float quote) 
	{
		this.ticker = ticker;
		this.quote = quote;
	}
	
}
