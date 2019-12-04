package com.techlabs.employee;

import java.util.*;

public class EmployeeComposite implements IEmployeeComposite {

    private Employee employee;
    private Set<IEmployeeComposite> reportees;
    
    public EmployeeComposite(Employee employee)
    {
	this.employee = employee;
	reportees = new HashSet<IEmployeeComposite>();
    }
    
    private String getLeadingWhitespace(int level)
    {
	StringBuilder builder = new StringBuilder("");
	for (int i = 0; i < level; i++)
	    builder.append('\t');
	return builder.toString();
    }
    
    public Employee getEmployee()
    {
	return this.employee;
    }
    
    @Override
    public int hashCode()
    {
	return this.employee.getId();
    }
    
    @Override
    public boolean equals(Object obj) {
	Employee other = ((EmployeeComposite) obj).getEmployee();
	return (this.employee.getId() == other.getId());
    }
    
    @Override
    public void display(int level) {
	System.out.printf("%s%s\n", getLeadingWhitespace(level), employee);
	for (IEmployeeComposite emp : reportees)
	    emp.display(level + 1);
    }

    @Override
    public void add(IEmployeeComposite emp) {
	this.reportees.add(emp);
    }

}
