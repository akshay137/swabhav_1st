package com.techlabs.employee;

import java.util.*;

public class EmployeeAnalyzer {
	
	public static Employee getHighestPaidEmployee(Collection<Employee> list)
	{
		if (list.size() == 0)
			return null;
		Employee highestPaid = null;
		double maxSalary = 0.0;
		for (Employee emp : list)
		{
			if (emp.getSalary() > maxSalary)
			{
				maxSalary = emp.getSalary();
				highestPaid = emp;
			}
		}
		return highestPaid;
	}
	
	public static int getCount(Collection<Employee> list,
			Comparator<Employee> cmp)
	{
		int count = 0;
		for (Employee emp : list)
		{
			if (cmp.equals(emp))
			{
				count++;
			}
		}
		return count;
	}

}
