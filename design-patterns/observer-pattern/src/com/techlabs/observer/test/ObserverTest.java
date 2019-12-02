package com.techlabs.observer.test;

import com.techlabs.publisher.*;
import com.techlabs.subscriber.*;

public class ObserverTest {
	public static void main(String[] args)
	{
		Account acc = new Account("abc", 500.0);
		acc.registerNotifier(SMSNotifier.getInstance());
		acc.registerNotifier(EmailNotifier.getInstance());
		acc.deposit(500.0);
		acc.withdraw(200.0);
	}
}
