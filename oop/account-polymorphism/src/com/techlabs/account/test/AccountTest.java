package com.techlabs.account.test;

import com.techlabs.account.*;

public class AccountTest {
	private static void printAccInfo(Account acc)
	{
		System.out.printf("acc_no: %d name: %s balance: %.2f\n",
				acc.getAccountNo(), acc.getName(), acc.getBalance());
	}
	
	public static void main(String[] args)
	{
		SavingsAccount sAcc = new SavingsAccount(1, "akshay", 500.0);
		printAccInfo(sAcc);
		
		sAcc.deposit(100.0);
		sAcc.withdraw(50.0);
		printAccInfo(sAcc);
		sAcc.withdraw(100.0);
		printAccInfo(sAcc);
		
		System.out.println();
		CurrentAccount cAcc = new CurrentAccount(2,  "akshay", 0.0);
		printAccInfo(cAcc);
		cAcc.deposit(50.0);
		printAccInfo(cAcc);
		cAcc.withdraw(15000.0);
		printAccInfo(cAcc);
		cAcc.withdraw(5000.0);
		printAccInfo(cAcc);
	}
}
