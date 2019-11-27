package com.techlabs.employee.test;

import com.techlabs.employee.data.*;
import com.techlabs.employee.io.*;

import java.util.Collections;
import java.util.Comparator;

import com.techlabs.employee.*;

public class EmployeeTest {
	private static final String filePath = "assets/dataFile.txt";
	private static final String fileURL = "https://swabhav-tech.firebaseapp.com/emp.txt";
	
	public static void main(String[] args)
	{
		DiskReader dr = new DiskReader(filePath);
		WebReader wr = new WebReader(fileURL);
		
		EmployeeList list = new EmployeeList();
		CSVParser parser = new CSVParser(list);
		list.addFromSource(dr, parser);
//		System.out.println(list.getEmployees().size());
//		list.addFromSource(wr, parser);
//		System.out.println(list.getEmployees().size());
//		System.out.printf("%s\n",
//				EmployeeAnalyzer.getHighestPaidEmployee(list.getEmployees()));
//		for (Employee emp : list.getEmployees())
//			System.out.println(emp);
//		EmployeeAnalyzer.getCount(list, new Comparator<Employee>() {
//			@Override
//			public boolean compare()
//		});
	}
}
