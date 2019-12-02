package com.techlabs.automobile.test;

import com.techlabs.automobile.*;

public class FactoryMethodTest {
	public static void main(String[] args)
	{
		IAutoFactory factory = new TeslaFactory();
		IAutoMobile auto = factory.make();
		auto.start();
	}
}
