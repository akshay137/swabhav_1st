package com.techlabs.employee;

import java.util.*;

public class EmployeeAnalyzer {
	
	private Collection<Employee> list;
	
	public EmployeeAnalyzer(Collection<Employee> list)
	{
		this.list = list;
	}

	public Employee getMax(Comparator<Employee> comparator) {
		return Collections.max(list, comparator);
	}
	
	public Employee getMin(Comparator<Employee> comparator) {
		return Collections.min(list, comparator);
	}

	public int countAll(Comparator<Object> comparator, Object condition)
	{
		int count = 0;
		for (Employee emp : list) {
			if (comparator.compare(emp, condition) == 0)
				count++;
		}
		return count;
	}

	public List<Employee> findAll(Comparator<Object> comparator,
			Object condition) {
		List<Employee> found = new ArrayList<Employee>();
		for (Employee emp : list) {
			if (comparator.compare(emp, condition) == 0)
				found.add(emp);
		}
		return found;
	}

	
//	pre-made comparators for ease of use
	
	public static final Comparator<Employee> compareSalary
		= new Comparator<Employee>() {
			
			@Override
			public int compare(Employee emp1, Employee emp2) {
				if (emp1.getSalary() == emp2.getSalary())
					return 0;
				return emp1.getSalary() < emp2.getSalary()
						? -1 : 1;
			}
		};
	
	public static final Comparator<Employee> compareCommision
		= new Comparator<Employee>() {
			
			@Override
			public int compare(Employee emp1, Employee emp2) {
				if (emp1.getCommision() == emp2.getCommision())
					return 0;
				return (emp1.getCommision() < emp2.getCommision())
						? -1 : 1;
			}
		};
	
	public static final Comparator<Object> matchDesignation
		= new Comparator<Object>() {
			@Override
			public int compare(Object emp, Object condition) {
				if (((Employee)emp).getDesignation()
						== (Designation) condition)
					return 0;
				return 1;
			}
		};

	public static final Comparator<Object> matchDepartment
		= new Comparator<Object>() {
			@Override
			public int compare(Object emp, Object condition) {
				if (((Employee)emp).getDepartmentId() == (int) condition)
					return 0;
				return (((Employee)emp).getDepartmentId() < (int) condition)
					? -1 : 1;
			}
		};

}
