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
	Map<Integer, IEmployeeComposite> empsmap = 
		new HashMap<Integer, IEmployeeComposite>();
	for (Employee e : list)
	{
	    IEmployeeComposite ec = new EmployeeComposite(e);
	    empsmap.put(e.getId(), ec);
	    if (e.getManagerId() == 0)
		this.root.add(ec);
	}
	for (IEmployeeComposite ec : empsmap.values())
	{
	    int managerId = ((EmployeeComposite)ec).getEmployee().getManagerId();
	    if (managerId == 0)
		continue;
	    IEmployeeComposite e = empsmap.get(managerId);
	    if (e != null)
		e.add(ec);
	}
	return this.root;
    }
    
    public Map<Integer, List<Employee>> build() {
	Map<Integer, List<Employee>> map = new HashMap<Integer, List<Employee>>();
	for (Employee e : list) {
	    map.put(e.getId(), new ArrayList<Employee>());
	}
	for (Employee e : list) {
	    List<Employee> lst = map.get(e.getManagerId());
	    lst.add(e);
	}
	return map;
    }
}
