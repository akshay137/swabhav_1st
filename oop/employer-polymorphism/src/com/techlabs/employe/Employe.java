package com.techlabs.employe;

public abstract class Employe {
	private int employeNo;
	private String name;
	protected double basicSalary;
	
	public Employe(int employe_no, String name, double basicSalary)
	{
		this.employeNo = employe_no;
		this.name = name;
		this.basicSalary = basicSalary;
	}
	
	public int getEmployeNo()
	{
		return this.employeNo;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getBasicSalary()
	{
		return this.basicSalary;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setBasicSalary(double basicSalary)
	{
		this.basicSalary = basicSalary;
	}
	
	public abstract double calculateNetSalary();
	public abstract String getSalarySlip();
	
	public String toString()
	{
		return String.format("e_no: %d name: %s basicSalary: %.2f",
				this.employeNo, this.name, this.basicSalary);
	}
}
