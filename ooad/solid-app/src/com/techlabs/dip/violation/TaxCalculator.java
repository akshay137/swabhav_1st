package com.techlabs.dip.violation;

public class TaxCalculator {
	int logType;
	
	public TaxCalculator(int logType)
	{
		this.setLogType(logType);
	}
	
	public void setLogType(int logType)
	{
		if (logType < 0 || logType > 1)
			logType = 0;
		this.logType = logType;
	}
	
	public int calculateTax(int amount, int rate)
	{
		try
		{
			return amount / rate;
		}
		catch (Exception e)
		{
			switch (this.logType)
			{
			case 0:
				XMLLogger loggerXML = new XMLLogger();
				loggerXML.log("error");
				break;
			
			case 1:
				TextLogger loggerTxt = new TextLogger();
				loggerTxt.log("error");
				break;
			}
			return -1;
		}
	}
}
