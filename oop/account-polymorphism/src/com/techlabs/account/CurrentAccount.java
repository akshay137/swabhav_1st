package com.techlabs.account;

public class CurrentAccount extends Account {

	private final int OVERDRAFT = -10000;
	
	public CurrentAccount(int acc_no, String name, double balance)
	{
		super(acc_no, name, balance);
	}
	
	@Override
	public void withdraw(double amount)
	{
		if (amount < 0.0)
			return;
		if ((this.balance - amount) < OVERDRAFT)
			return;
		this.balance -= amount;
	}

}
