package com.techlabs.accountts.test;

import com.techlabs.accountts.Account;

public class ToStringTest {
	public static void main(String[] args)
	{
		Account acc1 = new Account("0001", "akshay", 5000);
		Account acc2 = new Account("0001", "akshay", 5000);
		System.out.println(acc1.equals(acc2));
	}
}
