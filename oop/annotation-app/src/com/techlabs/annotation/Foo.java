package com.techlabs.annotation;

public class Foo {
	@NeedToRefactor
	public int return_one()
	{
		return 1;
	}
	
	@NeedToRefactor
	public int return_two()
	{
		return 2;
	}
	
	public int m3()
	{
		return 3;
	}
	
	@NeedToRefactor
	public int m4()
	{
		return 4;
	}
}
