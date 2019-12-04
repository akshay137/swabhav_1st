package com.techlabs.decorator.test;

import com.techlabs.decorator.*;

public class DecoratorTest {
	public static void main(String[] args)
	{
		IBikeService service = new TyreRotation(
				new OilChange(new BasicInspection()));
		System.out.printf("total cost %.2f\n", service.getCost());
		service.printDescription();
	}
}
