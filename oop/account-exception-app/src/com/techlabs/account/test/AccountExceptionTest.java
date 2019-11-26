package com.techlabs.account.test;

import com.techlabs.account.*;

public class AccountExceptionTest {
	public static void main(String[] args)
	{
		Account acc1 = new Account(1, "acc1", 500.0);
		try
		{
			acc1.withdraw(100.0);
		}
		catch (RuntimeException exception)
		{
			System.out.printf("Withdrawing funds: %s\n", exception.getMessage());
			exception.printStackTrace();
		}
	}
}
