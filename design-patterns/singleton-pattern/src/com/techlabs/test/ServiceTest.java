package com.techlabs.test;

import com.techlabs.normal.*;

public class ServiceTest {
	public static void main(String[] args)
	{
		DataService s1 = new DataService();
		DataService s2 = new DataService();
		s1.doSomething();
		s2.doSomething();
	}
}
