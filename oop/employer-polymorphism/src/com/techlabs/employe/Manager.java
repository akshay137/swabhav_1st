package com.techlabs.employe;

public class Manager extends Employe{
	private final double HRA = 0.5;
	private final double TA = 0.4;
	private final double DA = 0.3;
	
	public Manager(int employeNo, String name, double basicSalary)
	{
		super(employeNo, name, basicSalary);
	}
	
	@Override
	public double calculateNetSalary()
	{
		return this.basicSalary
				+ (this.basicSalary * HRA)
				+ (this.basicSalary * TA)
				+ (this.basicSalary * DA);
	}
	
	@Override
	public String getSalarySlip()
	{
		return String.format("%s\n\tHRA: %.2f TA: %.2f DA: %.2f\n\tnet: %.2f",
				super.toString(), this.basicSalary * HRA,
				this.basicSalary * TA,
				this.basicSalary * DA, calculateNetSalary());
	}
}
