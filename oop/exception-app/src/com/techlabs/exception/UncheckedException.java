package com.techlabs.exception;

public class UncheckedException {
	public static void main(String[] args)
	{
		try
		{
			m1();
		}
		catch (Exception exception)
		{
			System.out.printf("Exception: %s\n", exception.getMessage());
			exception.printStackTrace();
		}
		System.out.println("End of main");
	}
	
	private static void m1()
	{
		System.out.println("inside m1");
		m2();
	}
	
	private static void m2()
	{
		System.out.println("Inside m2");
		m3();
	}
	
	private static void m3()
	{
		System.out.println("Inside m3");
		throw new RuntimeException("Something went terribly wrong");
	}
}
