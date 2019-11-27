package com.techlabs.employee;

import java.text.*;
import java.util.*;
import com.techlabs.employee.data.*;

public class EmployeeList implements IParseAddable {
	private HashSet<Employee> employees;
	
	public EmployeeList()
	{
		this.employees = new HashSet<Employee>();
	}
	
	public boolean addEmployee(Employee employee)
	{
		return this.employees.add(employee);
	}
	
	private static int counter = 0;
	
	@Override
	public boolean add(Object ...objects)
	{
		System.out.printf("%d %s\n", counter++, objects);
		String[] values = (String[])objects;
		if (values.length < 8)
			return false;
		Employee emp = EmployeeBuilder.newEmployee(values[0],
				values[1], values[2], values[3], values[4],
				values[5], values[6], values[7]);
		if (emp != null)
		{
			employees.add(emp);
			return true;
		}
		return false;
	}
	
}
