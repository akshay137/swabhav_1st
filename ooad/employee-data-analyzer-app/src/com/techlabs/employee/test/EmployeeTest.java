package com.techlabs.employee.test;

import com.techlabs.employee.data.*;
import com.techlabs.employee.io.*;
import com.techlabs.employee.*;

public class EmployeeTest {
	private static final String filePath = "assets/dataFile.txt";
	private static final String fileURL = "https://swabhav-tech.firebaseapp.com/emp.txt";
	
	public static void main(String[] args)
	{
		DiskReader dr = new DiskReader();
		String data = dr.readFile(filePath);
//		WebReader wr = new WebReader();
//		data = wr.readFile(fileURL);
		
		EmployeeList list = new EmployeeList();
		
		CSVParser parser = new CSVParser(list);
		parser.parse(data);
	}
}
