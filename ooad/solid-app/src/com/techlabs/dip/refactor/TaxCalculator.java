package com.techlabs.dip.refactor;

public class TaxCalculator {
	private Logger logger;
	
	public TaxCalculator(Logger logger)
	{
		this.logger = logger;
	}
	
	public void setLogger(Logger logger)
	{
		this.logger = logger;
	}
	
	public int calculateTax(int amnt, int rate)
	{
		try
		{
			return amnt / rate;
		}
		catch (Exception e)
		{
			logger.log("error occured");
			return -1;
		}
	}
}
