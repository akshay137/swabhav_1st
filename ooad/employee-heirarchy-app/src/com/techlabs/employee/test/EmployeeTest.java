package com.techlabs.employee.test;

import com.techlabs.employee.data.*;
import com.techlabs.employee.io.*;
import com.techlabs.employee.*;

import java.util.*;


public class EmployeeTest {
    private static final String filePath = "assets/dataFile.txt";

    public static void main(String[] args) {
	DiskReader dr = new DiskReader(filePath);

	EmployeeList list = new EmployeeList();
	CSVParser parser = new CSVParser(list);
	list.addFromSource(dr, parser);

	HeirarchyBuilder hb = new HeirarchyBuilder(list.getEmployees());
	List<IEmployeeComposite> ceos = hb.buildHeirarchy();
	for (IEmployeeComposite ceo : ceos)
	    ceo.display(0);
    }
}
