package com.techlabs;

public class ArgumentTest {
	public static void main(String[] args)
	{
		if (args.length == 0)
		{
			System.out.println("No arguments provided");
			System.out.println("Please pass arguments");
			return;
		}
		
		for (String arg : args)
		{
			System.out.println("Hello " + arg);
		}
	}
}
