package com.techlabs.employe.test;

import com.techlabs.employe.*;

public class EmployeTest {
	public static void main(String[] args)
	{
		Manager manager = new Manager(1, "manager", 50000.0);
		Programmer programmer = new Programmer(2, "programmer", 40000.0);
		Accountant accountant = new Accountant(3, "accountant", 45000.0);
		
		System.out.println("Net Salaries:");
		System.out.printf("Manager: %.2f\n", manager.calculateNetSalary());
		System.out.printf("Programmer: %.2f\n", programmer.calculateNetSalary());
		System.out.printf("Accountant: %.2f\n", accountant.calculateNetSalary());
		
		System.out.println("\nSalry Slips");
		printSalarySlip(manager);
		printSalarySlip(programmer);
		printSalarySlip(accountant);
	}
	
	private static void printSalarySlip(Employe employe)
	{
		System.out.println(employe.getSalarySlip());
	}
}
