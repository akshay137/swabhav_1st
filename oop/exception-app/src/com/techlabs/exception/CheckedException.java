package com.techlabs.exception;

public class CheckedException {
	public static void main(String[] args)
	{
		try
		{
			m1();
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
			exception.printStackTrace();
		}
		System.out.println("End of main");
	}
	
	private static void m1() throws Exception
	{
		System.out.println("inside m1");
		m2();
	}
	
	private static void m2() throws Exception
	{
		System.out.println("Inside m2");
		m3();
	}
	
	private static void m3() throws Exception
	{
		System.out.println("Inside m3");
		throw new Exception("Something went wrong");
	}
}
