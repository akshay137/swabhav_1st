package com.techlabs.engineering.test;

import com.techlabs.engineering.*;

public class EngineeringTest {
	private static void printSalary(SalariedEmployee employee)
	{
		System.out.printf("salary: %.2f\n", employee.calculateSalary());
	}
	
	private static void printPersonInfo(Person person)
	{
		System.out.println(person);
	}
	
	public static void main(String[] args)
	{
		Professor mathProf = new Professor(1, "mumbai", "20 jan 1990", 25000);
		Professor csProf = new Professor(2, "panvel", "20 feb 1885", 35000);
		printPersonInfo(mathProf);
		printSalary(mathProf);
		printPersonInfo(csProf);
		printSalary(csProf);
		Student s1 = new Student(1, "mumbai", "20 march 1995", Branch.Computer);
		Student s2 = new Student(2, "kolhapur", "20 april 1996", Branch.Civil);
		printPersonInfo(s1);
		printPersonInfo(s2);
	}
}
