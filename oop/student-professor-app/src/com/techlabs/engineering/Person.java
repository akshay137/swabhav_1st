package com.techlabs.engineering;

public abstract class Person {
	private int id;
	private String address;
	private String dob;
	
	public Person(int id, String address, String dob)
	{
		this.id = id;
		this.address = address;
		this.dob = dob;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public String getDob()
	{
		return this.dob;
	}
	
	@Override
	public String toString()
	{
		return String.format("id: %d address: %s dob: %s",
				this.id, this.address, this.dob);
	}
}
