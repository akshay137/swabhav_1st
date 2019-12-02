package com.techlabs.test;

import com.techlabs.singleton.*;

public class SingletonTest {
	public static void main(String[] args)
	{
		DataService s1 = DataService.getInstance();
		DataService s2 = DataService.getInstance();
		s1.doSomething();
		s2.doSomething();
	}
}
