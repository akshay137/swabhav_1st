package com.techlabs.account.customException.test;

import com.techlabs.account.customException.*;

public class CustomExceptionTest {
	public static void main(String[] args)
	{
		Account acc1 = new Account(1, "acc1", 500.0);
		try
		{
			acc1.withdraw(200.0);
		}
		catch (InSufficientFundsException exception)
		{
			System.out.printf("exception: %s\n", exception.getMessage());
			exception.printStackTrace();
		}
	}
}
