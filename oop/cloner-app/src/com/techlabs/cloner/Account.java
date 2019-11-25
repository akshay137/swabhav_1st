package com.techlabs.cloner;

public class Account implements Cloneable {
	private int accNo;
	private String name;
	protected double balance;
	
	@Override
	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException exception)
		{
			return null;
		}
	}
	
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
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return String.format("%x no: %d name: %s balance: %.2f", hashCode(),
				this.accNo, this.name, this.balance);
	}
}
