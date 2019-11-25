package com.techlabs.account;

import java.io.*;

public abstract class Account implements Serializable {
	private int acc_no;
	private String name;
	protected double balance;
	
	public Account(int acc_no, String name, double balance)
	{
		this.acc_no = acc_no;
		this.name = name;
		this.balance = balance;
	}
	
	public int getAccountNo()
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
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void deposit(double amount)
	{
		if (amount < 0.0)
			return;
		this.balance += amount;
	}
	
	public abstract void withdraw(double amount);
	
	@Override
	public String toString()
	{
		return String.format("hash: %x acc_no: %d name: %s balance: %.2f",
				hashCode(), this.acc_no, this.name, this.balance);
	}
}
