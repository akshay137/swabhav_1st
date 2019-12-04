package com.techlabs.proxy;

public class AccountLogProxy implements IAccount {
	
	private Account account;
	private ILogger logger;
	
	public AccountLogProxy(Account account)
	{
		this(account, null);
	}
	
	public AccountLogProxy(Account account, ILogger logger)
	{
		this.account = account;
		this.setLogger(logger);
	}
	
	public void setLogger(ILogger logger)
	{
		if (logger == null)
			logger = new ConsoleLogger();
		this.logger = logger;
	}
	
	public IAccount getAccount()
	{
		return this.account;
	}

	@Override
	public void deposit(double amount) {
		logger.log("deposit started");
		account.deposit(amount);
		logger.log("deposit complete");
	}

	@Override
	public void withdraw(double amount) {
		logger.log("withdrawl started"); 
		account.withdraw(amount);
		logger.log("withdrawl complete");
	}
	
	@Override
	public String toString()
	{
		return this.account.toString();
	}

	@Override
	public int getId() {
		return this.account.getId();
	}

	@Override
	public String getName() {
		return this.account.getName();
	}

	@Override
	public double getBalance() {
		return this.account.getBalance();
	}

}
