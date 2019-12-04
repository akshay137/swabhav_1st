package com.techlabs.employee;

import java.text.*;
import java.util.*;

public class EmployeeBuilder {
    public static Employee newEmployee(String[] values) {
	if (values.length < 8)
	    return null;

	int id = parseId(values[0]);
	if (id < 0)
	    return null;

	String name = values[1].replace("'", "");

	Designation designation = parseDesignation(values[2].replace("'", ""));
	if (designation == Designation.NONE)
	    return null;

	int managerId = parseId(values[3]);
	if (managerId < -1)
	    return null;

	Date dateOfJoining = parseDate(values[4]);
	if (dateOfJoining == null)
	    return null;

	double salary = parseDouble(values[5]);
	if (salary < 0.0)
	    return null;

	double commision = parseDouble(values[6]);
	if (commision < 0.0)
	    return null;

	int departmentId = parseId(values[7]);
	if (departmentId < 0)
	    return null;

	return new Employee(id, name, designation, managerId, dateOfJoining, salary, commision, departmentId);
    }

    private static int parseId(String value) {
	if (value == null)
	    return 0;
	if (value.length() == 0)
	    return 0;
	if (value.toLowerCase().equals("null"))
	    return 0;
	try {
	    return Integer.parseInt(value);
	} catch (NumberFormatException exception) {
	    return -1;
	}
    }

    private static Designation parseDesignation(String value) {
	try {
	    return Designation.valueOf(value);
	} catch (IllegalArgumentException exception) {
	    return Designation.NONE;
	}
    }

    private static Date parseDate(String value) {
	value = value.replace("'", "");
	String[] values = value.split("-");
	if (values.length < 3)
	    return null;
	try {
	    Calendar c = Calendar.getInstance();
	    c.setTime(new SimpleDateFormat("dd-MMM-yy").parse(value));
	    return c.getTime();
	} catch (ParseException exception) {
	    return null;
	}
    }

    private static double parseDouble(String value) {
	if (value == null)
	    return 0.0;
	if (value.length() == 0)
	    return 0.0;
	if (value.toLowerCase().equals("null"))
	    return 0.0;
	try {
	    return Double.parseDouble(value);
	} catch (NumberFormatException exception) {
	    return -1.0;
	}
    }
}
