package com.techlabs.subscriber;

import com.techlabs.publisher.Account;
import com.techlabs.publisher.IBalanceNotifier;

public class SMSNotifier implements IBalanceNotifier {
	
	private static SMSNotifier instance;
	
	private SMSNotifier() {}
	
	public static SMSNotifier getInstance()
	{
		if (instance == null)
			instance = new SMSNotifier();
		return instance;
	}

	@Override
	public void notifyBalance(Account acc, String msg) {
		System.out.printf("SMS notification: %s\n", msg);
		System.out.printf("id:%d balance:%.2f\n",
				acc.getId(), acc.getBalance());
		System.out.println();
	}

}
