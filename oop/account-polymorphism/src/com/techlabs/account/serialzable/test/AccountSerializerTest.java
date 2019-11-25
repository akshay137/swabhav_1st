package com.techlabs.account.serialzable.test;

import com.techlabs.account.*;
import com.techlabs.account.serializer.*;

public class AccountSerializerTest {
	public static void main(String[] args)
	{
		SavingsAccount acc = new SavingsAccount(1, "testname", 500.0);
		System.out.println(acc);
		boolean status = AccountSerializer.serializeAccount(acc, "sacc.ser");
		System.out.println("Serialized: " + status);
		SavingsAccount nacc =
				(SavingsAccount)AccountSerializer.deserializeAccount("sacc.ser");
		System.out.println(nacc);
	}
}
