package com.techlabs.engineering;

public class Professor extends Person implements SalariedEmployee {

	private double basicSalary;
	
	public Professor(int id, String address, String dob, double basicSalary)
	{
		super(id, address, dob);
		this.basicSalary = basicSalary;
	}
	
	@Override
	public double calculateSalary()
	{
		return this.basicSalary;
	}

}
