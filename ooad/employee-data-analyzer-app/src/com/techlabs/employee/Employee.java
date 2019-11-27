package com.techlabs.employee;

import java.util.*;

public class Employee {
	private int id;
	private String name;
	private Designation desgination;
	private int managerId;
	private Date dateOfJoining;
	private double salary;
	private double commision;
	private int departmentId;
	
	public Employee(int id, String name, Designation desgination,
			int managerId, Date dateOfJoining, double salary,
			double commision, int department)
	{
		this.setId(id);
		this.setName(name);
		this.setDesignation(desgination);
		this.setManagerId(managerId);
		this.setDateOfJoining(dateOfJoining);
		this.setSalary(salary);
		this.setCommision(commision);
		this.setDepartmentId(department);
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Designation getDesignation()
	{
		return this.desgination;
	}
	
	public void setDesignation(Designation designation)
	{
		this.desgination = designation;
	}
	
	public int getManagerId()
	{
		return this.managerId;
	}
	
	public void setManagerId(int managerId)
	{
		this.managerId = managerId;
	}
	
	public Date getDateOfJoining()
	{
		return this.dateOfJoining;
	}
	
	public void setDateOfJoining(Date date)
	{
		this.dateOfJoining = date;
	}
	
	public double getSalary()
	{
		return this.salary;
	}
	
	public void setSalary(double salary)
	{
		if (salary < 0.0)
			return;
		this.salary = salary;
	}
	
	public double getCommision()
	{
		return this.commision;
	}
	
	public void setCommision(double commision)
	{
		if (commision < 0.0)
			return;
		this.commision = commision;
	}
	
	public int getDepartmentId()
	{
		return this.departmentId;
	}
	
	public void setDepartmentId(int departmentId)
	{
		this.departmentId = departmentId;
	}
	
	@Override
	public String toString()
	{
		return String.format("%d:%s:%.2f::%tc",
				this.id, this.name, this.salary, this.dateOfJoining);
	}
	
	@Override
	public int hashCode()
	{
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Employee other = (Employee)obj;
		return (this.id == other.getId())
				&& (this.departmentId == other.getDepartmentId());
	}
}
