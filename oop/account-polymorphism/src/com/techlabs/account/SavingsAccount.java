package com.techlabs.account;

import java.io.*;

public class SavingsAccount extends Account {

	private final int MIN_BALANCE = 500;
	
	public SavingsAccount(int acc_no, String name, double balance)
	{
		super(acc_no, name, balance);
	}
	
	@Override
	public void withdraw(double amount)
	{
		if (amount < 0.0)
			return;
		if ((this.balance - amount) < MIN_BALANCE)
			return;
		this.balance -= amount;
	}
}
