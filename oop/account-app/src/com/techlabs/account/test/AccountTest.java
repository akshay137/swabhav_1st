package com.techlabs.account.test;

import com.techlabs.account.*;

public class AccountTest {
	public static void main(String[] args)
	{
		Account acc1 = new Account("0001", "first", 1000.0);
		Account acc2 = new Account("0002", "second");
		
		System.out.println("acc1 balance = " + acc1.getBalance());
		acc1.withdraw(1200);
		System.out.println("acc1 balance after withdraw = " + acc1.getBalance());
		acc1.withdraw(200);
		System.out.println("acc1 balance after withdraw = " + acc1.getBalance());
		
		System.out.println("acc2 balance = " + acc2.getBalance());
		acc2.deposit(200);
		System.out.println("acc2 balance after deposit = " + acc2.getBalance());
	}
}
