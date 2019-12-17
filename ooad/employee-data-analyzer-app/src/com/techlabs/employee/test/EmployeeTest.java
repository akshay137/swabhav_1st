package com.techlabs.employee.test;

import com.techlabs.employee.data.*;
import com.techlabs.employee.io.*;

import java.util.*;

import com.techlabs.employee.*;

public class EmployeeTest {
	private static final String filePath = "assets/dataFile.txt";
	private static final String fileURL = "https://swabhav-tech.firebaseapp.com/emp.txt";

	public static void main(String[] args) {
		DiskReader dr = new DiskReader(filePath);
		WebReader wr = new WebReader(fileURL);

		EmployeeList list = new EmployeeList();
		CSVParser parser = new CSVParser(list);
		list.addFromSource(dr, parser);
//		list.addFromSource(wr, parser);
		EmployeeAnalyzer analyzer = new EmployeeAnalyzer(
			list.getEmployees());
		
		Employee highestPaid = analyzer.getMax(
			EmployeeAnalyzer.compareSalary);
		System.out.println("higest paid: " + highestPaid);
		Employee lowestPaid = analyzer.getMin(
			EmployeeAnalyzer.compareSalary);
		System.out.println("lowest paid: " + lowestPaid);
		
		int count = analyzer.countAll(
			EmployeeAnalyzer.matchDepartment, 20);
		System.out.printf("employees in department 20: %d\n", count);
		count = analyzer.countAll(EmployeeAnalyzer.matchDesignation,
				Designation.CLERK);
		System.out.printf("Employees with designation CLERK: %d\n",
			count);
		
		Map<Designation, Integer> countMap =
			analyzer.getDepartmentWiseCount();
		displayMap(countMap);
	}
	
	private static <E, T> void displayMap(Map<E, T> map) {
	    for (E key : map.keySet()) {
		System.out.printf("%s: %s\n", key, map.get(key));
	    }
	}
}
