package com.techlabs.constructor.cas2;

public class Parent {
	public int foo;
	public Parent(int foo)
	{
		this.foo = foo;
		System.out.println("inside parent");
	}
	
	public int getFoo()
	{
		return this.foo;
	}
}
