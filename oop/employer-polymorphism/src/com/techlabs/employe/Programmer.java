package com.techlabs.employe;

public class Programmer extends Employe {
	private final double BONUS = 0.4;
	private final double PA = 0.3;
	
	public Programmer(int employeNo, String name, double basicSalary)
	{
		super(employeNo, name, basicSalary);
	}
	
	@Override
	public double calculateNetSalary()
	{
		return this.basicSalary
				+ (this.basicSalary * BONUS)
				+ (this.basicSalary * PA);
	}
	
	@Override
	public String getSalarySlip()
	{
		return String.format("%s\n\tBonus: %.2f PA: %.2f\n\tnet: %.2f",
				super.toString(),
				this.basicSalary * BONUS, this.basicSalary * PA,
				calculateNetSalary());
	}
}
