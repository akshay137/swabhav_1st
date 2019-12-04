package com.techlabs.proxy;

public interface IAccount {

	int getId();

	String getName();

	double getBalance();

	void deposit(double amount);

	void withdraw(double amount);

	String toString();

}