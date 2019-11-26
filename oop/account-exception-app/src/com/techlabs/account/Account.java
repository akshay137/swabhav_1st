package com.techlabs.account;

public class Account {
	private int accNo;
	private String name;
	private double balance;
	
	private static final double MIN_BALANCE = 500.0;
	
	public Account(int accNo, String name, double balance)
	{
		this.accNo = accNo;
		this.name = name;
		this.balance = balance;
	}
	
	public int getAccNo()
	{
		return this.accNo;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getBalance()
	{
		return this.balance;
	}
	
	public void deposite(double amount)
	{
		if (amount < 0.0)
			return;
		this.balance += amount;
	}
	
	public void withdraw(double amount)
	{
		if (amount < 0.0)
			return;
		if (this.balance - amount < MIN_BALANCE)
			throw new RuntimeException("No funds to withdraw");
		this.balance -= amount;
	}
}
