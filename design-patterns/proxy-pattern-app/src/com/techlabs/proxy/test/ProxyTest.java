package com.techlabs.proxy.test;

import com.techlabs.proxy.*;

public class ProxyTest {
	private static void doTransaction(IAccount acc)
	{
		acc.deposit(1000);
		acc.withdraw(500);
		System.out.printf("name: %s\n", acc.getName());
		System.out.printf("balance: %.2f\n", acc.getBalance());
	}
	
	public static void main(String[] args)
	{
		Account acc1 = new Account("abc", 5000.0);
		doTransaction(acc1);
		AccountLogProxy proxy = new AccountLogProxy(acc1);
		doTransaction(proxy);
	}
}
