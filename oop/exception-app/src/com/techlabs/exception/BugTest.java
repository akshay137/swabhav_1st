package com.techlabs.exception;

import java.io.IOException;

import javax.sql.rowset.serial.SerialException;

public class BugTest {
	public static void main(String[] args)
	{
		try
		{
			int a = Integer.parseInt(args[0]);
			int b = Integer.parseInt(args[1]);
			int c = a / b;
			System.out.printf("Result: %d\n", c);
			System.out.println("End of program");
		}
		catch (Exception exception)
		{
			System.out.println(exception);
			exception.printStackTrace();
		}
	}
}
