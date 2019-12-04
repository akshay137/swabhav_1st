package com.techlabs.employee;

import java.util.*;

public class HeirarchyBuilder {
    private List<IEmployeeComposite> root;
    private Set<Employee> list;
    
    public HeirarchyBuilder(Set<Employee> list)
    {
	this.list = list;
	this.root = new ArrayList<IEmployeeComposite>();
    }
    
    public List<IEmployeeComposite> buildHeirarchy()
    {
	List<IEmployeeComposite> emps = new ArrayList<IEmployeeComposite>();
	for (Employee e : list)
	{
	    IEmployeeComposite ec = new EmployeeComposite(e);
	    if (e.getManagerId() == 0)
		this.root.add(ec);
	    emps.add(ec);
	}
	for (IEmployeeComposite ec : emps)
	{
	    int managerId = ((EmployeeComposite)ec).getEmployee().getManagerId();
	    for (IEmployeeComposite e : emps)
	    {
		if (((EmployeeComposite)e).getEmployee().getId() == managerId)
		{
		    e.add(ec);
		    break;
		}
	    }
	}
	return this.root;
    }
}
