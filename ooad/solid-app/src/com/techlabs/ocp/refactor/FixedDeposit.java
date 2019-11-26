package com.techlabs.ocp.refactor;


public class FixedDeposit {
	private String name;
	private double principleAmount;
	private int durationInYears;
	private IFestivalRate festival;
	
	public FixedDeposit(String name, double amount, int years,
			IFestivalRate fest)
	{
		this.name = name;
		this.festival = fest;
		setAmount(amount);
		setDuration(years);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	private void setAmount(double amount)
	{
		if (amount < 0.0)
			return;
		this.principleAmount = amount;
	}
	
	private void setDuration(int years)
	{
		if (years < 0)
			return;
		this.durationInYears = years;
	}
	
	public double calculateSimpleInterest()
	{
		return this.principleAmount * festival.getRate()
				* this.durationInYears / 100.0;
	}
}
