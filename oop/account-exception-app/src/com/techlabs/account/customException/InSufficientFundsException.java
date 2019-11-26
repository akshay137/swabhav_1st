package com.techlabs.account.customException;

public class InSufficientFundsException extends RuntimeException {
	
	private Account acc;
	private double amount;
	
	public InSufficientFundsException(Account acc, double amount)
	{
		this.acc = acc;
		this.amount = amount;
	}
	
	@Override
	public String getMessage()
	{
		return String.format("Tried to withdraw %.2f from %.2f %s\n",
				acc.getBalance(), amount,
				"which would take acc balance to below minimum allowed");
	}
}
