package com.techlabs.employe;

public class Accountant extends Employe {
	
	private final double PERKS = 0.3;
	
	public Accountant(int employeNo, String name, double basicSalary)
	{
		super(employeNo, name, basicSalary);
	}
	
	@Override
	public double calculateNetSalary()
	{
		return this.basicSalary
				+ (this.basicSalary * PERKS);
	}
	
	@Override
	public String getSalarySlip()
	{
		return String.format("%s\n\tPerks: %.2f\n\tnet: %.2f",
				super.toString(), this.basicSalary * PERKS,
				calculateNetSalary());
	}

}
