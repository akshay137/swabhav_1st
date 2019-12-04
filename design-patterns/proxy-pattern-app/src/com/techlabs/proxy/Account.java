package com.techlabs.proxy;

public class Account implements IAccount {
	
	private int id;
	private String name;
	private double balance;
	
	private static int idCounter = 0;
	private static final int MIN_BALANCE = 500;
	
	public Account(String name, double balance)
	{
		this.id = idCounter++;
		this.name = name;
		this.balance = balance;
	}
	
	@Override
	public int getId()
	{
		return this.id;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public double getBalance()
	{
		return this.balance;
	}

	@Override
	public void deposit(double amount) {
		if (amount < 0)
			return;
		this.balance += amount;
	}

	@Override
	public void withdraw(double amount) {
		if (amount < 0)
			return;
		if (this.balance - amount < MIN_BALANCE)
			return;
		this.balance -= amount;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s: balance:%.2f", this.name, this.balance);
	}

}
