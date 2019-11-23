package com.techlabs.account;

public class Account {
	private String acc_no;
	private String name;
	private double balance;
	
	private static final int MIN_BALANCE = 500;
	
	public Account(String acc_no, String name, double balance)
	{
		this.acc_no = acc_no;
		this.name = name;
		this.balance = balance;
	}
	
	public Account(String acc_no, String name)
	{
		this(acc_no, name, MIN_BALANCE);
	}
	
	public void deposit(double amount)
	{
		if (amount < 0)
			return;
		this.balance += amount;
	}
	
	public void withdraw(double amount)
	{
		if (amount < 0)
			return;
		if (this.balance - amount < MIN_BALANCE)
		{
			System.out.println("Withdrawing will cause balnce to go below Mnimum balance");
			return;
		}
		this.balance -= amount;
	}
	
	public String getAccountNumber()
	{
		return this.acc_no;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getBalance()
	{
		return this.balance;
	}
}
