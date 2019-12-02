package com.techlabs.singleton;

public class DataService {
	private static DataService instance;
	
	private DataService()
	{
		System.out.println("Service created");
	}
	
	public static DataService getInstance()
	{
		if (instance == null)
			instance = new DataService();
		return instance;
	}
	
	public void doSomething()
	{
		System.out.printf("Service %d is doing something\n", this.hashCode());
	}
	
}
