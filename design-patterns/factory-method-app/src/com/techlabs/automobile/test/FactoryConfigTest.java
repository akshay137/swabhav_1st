package com.techlabs.automobile.test;

import com.techlabs.automobile.singleton.*;

public class FactoryConfigTest {
	public static void main(String[] args)
	{
		FactoryConfig config = new FactoryConfig();
		IAutoFactory factory = config.getFactory();
		System.out.printf("factory: %s\n",
				factory.getClass().getSimpleName());
		IAutoMobile auto = factory.make();
		System.out.printf("automobile: %s\n",
				auto.getClass().getSimpleName());
		auto.start();
		auto.stop();
	}
}
