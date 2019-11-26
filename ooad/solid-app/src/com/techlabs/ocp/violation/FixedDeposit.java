package com.techlabs.ocp.violation;

import com.techlabs.ocp.*;

public class FixedDeposit {
	private String name;
	private double principleAmount;
	private int durationInYears;
	private Festival festival;
	
	public FixedDeposit(String name, double amount, int years, Festival fest)
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
	
	private float getInterestRate(Festival fest)
	{
		switch (fest)
		{
		case NORMAL:
			return 0.07f;
			
		case HOLI:
			return 0.08f;
			
		case NEW_YEAR:
			return 0.09f;
		}
		return 0.07f;
	}
	
	public double calculateSimpleInterest()
	{
		return this.principleAmount * getInterestRate(festival)
				* this.durationInYears;
	}
}
