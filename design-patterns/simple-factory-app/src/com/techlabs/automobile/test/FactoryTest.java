package com.techlabs.automobile.test;

import com.techlabs.automobile.*;

public class FactoryTest {
	public static void main(String[] args)
	{
		System.out.println("normal factory");
		AutoMobileFactory factory = new AutoMobileFactory();
		IAutoMobile bmw = factory.make(AutoMobileType.BMW);
		System.out.printf("bmw class: %s\n", bmw.getClass());
		bmw.start();
		bmw.stop();
		
		System.out.println("Singleton factory");
		IAutoMobile tesla = AutoFactory.getInstance().make(AutoMobileType.Tesla);
		System.out.printf("bmw class: %s\n", tesla.getClass());
		tesla.start();
		tesla.stop();
	}
}
