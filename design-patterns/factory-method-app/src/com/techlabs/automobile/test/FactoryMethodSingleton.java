package com.techlabs.automobile.test;

import com.techlabs.automobile.singleton.*;

public class FactoryMethodSingleton {
	public static void main(String[] args)
	{
		IAutoFactory factory = TeslaFactory.getInstance();
		IAutoMobile auto = factory.make();
		auto.start();
	}
}
