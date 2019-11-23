package com.techlabs.polymorphing.test;

import com.techlabs.polymorphing.*;

public class PolymorphingTest {
	public static void main(String[] args)
	{
//		case1();
//		case2();
//		case3();
//		case4();
		case5();
	}
	
	private static void case1()
	{
		Man x;
		x = new Man();
		x.play();
		x.read();
	}
	
	private static void case2()
	{
		Boy y;
		y = new Boy();
		y.play();
		y.walk();
		y.read();
	}
	
	private static void case3()
	{
		Man x;
		x = new Boy();
		x.play();
		x.read();
	}
	
	private static void atThePark(Man x)
	{
		x.play();
	}
	
	private static void case4()
	{
		atThePark(new Man());
		atThePark(new Boy());
		atThePark(new Kid());
		atThePark(new Infant());
	}
	
	private static void case5()
	{
		Object x;
		x = 10;
		System.out.println(x.getClass());
		x = "hello";
		System.out.println(x.getClass());
		x = new Man();
		System.out.println(x.getClass());
		x = new Infant();
		System.out.println(x.getClass());
	}
}
