package com.techlabs.publisher;

import java.util.*;

public class Account {
	private int id;
	private String name;
	private double balance;
	
	private List<IBalanceNotifier> subscribers;
	
	private static int idCounter = 0;
	private static final double MIN_BALANCE = 500.0;
	
	public Account(String name, double balance)
	{
		this.id = idCounter++;
		this.name = name;
		this.setBalance(balance);
		this.subscribers = new ArrayList<IBalanceNotifier>();
	}
	
	private void setBalance(double balance)
	{
		if (balance < MIN_BALANCE)
			return;
		this.balance = balance;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public double getBalance()
	{
		return this.balance;
	}
	
	public boolean registerNotifier(IBalanceNotifier notifier)
	{
		if (notifier == null)
			return false;
		return this.subscribers.add(notifier);
	}
	
	public void deposit(double amount)
	{
		if (amount < 0.0)
			return;
		this.balance += amount;
		sendNotification(String.format("deposit: %.2f", amount));
	}
	
	public void withdraw(double amount)
	{
		if (amount < 0.0)
			return;
		if (this.balance - amount < MIN_BALANCE)
			return;
		this.balance -= amount;
		sendNotification(String.format("withdraw: %.2f", amount));
	}
	
	private void sendNotification(String msg)
	{
		for (IBalanceNotifier notifier : this.subscribers)
			notifier.notifyBalance(this, msg);
	}
	
	
}
