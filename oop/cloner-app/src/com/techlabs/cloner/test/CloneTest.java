package com.techlabs.cloner.test;

import com.techlabs.cloner.*;

public class CloneTest {
	public static void main(String[] args)
	{
		Account acc1 = new Account(0, "a", 15.0);
		Account acc2 = (Account)acc1.clone();
		System.out.printf("acc1: %s\n", acc1);
		System.out.printf("acc2: %s\n", acc2);
	}
}
