package com.techlabs;

public class WelcomeTest {
	public static void main(String[] args)
	{
		printInfo(false);
		printInfo('c');
		printInfo(1);
		printInfo(1.0f);
		printInfo("string");
	}
	
	public static void printInfo(char c)
	{
		System.out.println(c);
	}
	
	public static void printInfo(int i)
	{
		System.out.println(i);
	}
	
	public static void printInfo(float f)
	{
		System.out.println(f);
	}
	
	public static void printInfo(String s)
	{
		System.out.println(s);
	}
	
	public static void printInfo(boolean b)
	{
		System.out.println(b);
	}
}
