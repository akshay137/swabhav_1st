package com.techlabs.subscriber;

import com.techlabs.publisher.Account;
import com.techlabs.publisher.IBalanceNotifier;

public class EmailNotifier implements IBalanceNotifier {
	
	private static EmailNotifier instance;
	
	private EmailNotifier() {}
	
	public static EmailNotifier getInstance()
	{
		if (instance == null)
			instance = new EmailNotifier();
		return instance;
	}

	@Override
	public void notifyBalance(Account acc, String msg) {
		System.out.printf("Email notification: %s\n", msg);
		System.out.println(msg);
		System.out.printf("id:%d balance:%.2f\n",
				acc.getId(), acc.getBalance());
		System.out.println();
	}

}
