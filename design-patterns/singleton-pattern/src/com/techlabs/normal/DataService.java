package com.techlabs.normal;

public class DataService {
	public DataService()
	{
		System.out.println("Service is created");
	}
	
	public void doSomething()
	{
		System.out.printf("Service %d is doing something\n", this.hashCode());
	}
}
