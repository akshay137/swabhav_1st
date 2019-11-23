package com.techlabs.reflector;

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
	
	@Override
	public String toString()
	{
		return super.toString() + " acc_no: " + this.acc_no + ", "
				+ "name: " + this.name + ", "
				+ "balance: " + this.balance;
	}
	
	@Override
	public int hashCode()
	{
		return Integer.parseInt(this.acc_no);
	}
	
	@Override
	public boolean equals(Object other)
	{
		return this.acc_no == ((Account)other).acc_no
				&& this.name == ((Account)other).name
				&& this.balance == ((Account)other).balance;
	}
}
