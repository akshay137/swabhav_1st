package com.techlabs.constructor.cas2;

public class Child extends Parent {
	public Child()
	{
		super(100);
		System.out.println("Inside child");
	}
	
	public Child(int foo)
	{
		super(foo);
		System.out.println("Inside child");
	}
}
