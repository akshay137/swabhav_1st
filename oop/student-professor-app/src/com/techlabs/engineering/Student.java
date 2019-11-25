package com.techlabs.engineering;

public class Student extends Person {
	private Branch branch;
	
	public Student(int id, String address, String dob, Branch branch)
	{
		super(id, address, dob);
		this.branch = branch;
	}
	
	public Branch getBranch()
	{
		return this.branch;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s branch: %s", super.toString(), this.branch);
	}
}
