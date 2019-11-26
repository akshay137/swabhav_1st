package com.techlabs.ch1;

public class Guitar {
	private GuitarSpec specs;
	private String serialNumber;
	private double price;
	
	public Guitar(GuitarSpec specs, String serialNumber, double price)
	{
		this.specs = specs;
		this.serialNumber = serialNumber;
		this.price = price;
	}
	
	public GuitarSpec getSpecs()
	{
		return this.specs;
	}
	
	public String getSerialNumber()
	{
		return this.serialNumber;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public void setPrice(double price)
	{
		if (price < 0.0)
			return;
		this.price = price;
	}
}
