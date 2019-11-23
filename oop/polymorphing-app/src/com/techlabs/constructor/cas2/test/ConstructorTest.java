package com.techlabs.constructor.cas2.test;

import com.techlabs.constructor.cas2.*;

public class ConstructorTest {
	public static void main(String[] args)
	{
		Child c1 = new Child();
		Child c2 = new Child(200);
		System.out.println(c1.getFoo());
		System.out.println(c2.getFoo());
	}
}
