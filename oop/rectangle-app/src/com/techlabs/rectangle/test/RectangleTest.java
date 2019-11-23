package com.techlabs.rectangle.test;
import com.techlabs.rectangle.*;

public class RectangleTest {
	public static void main(String[] args)
	{
		Rectangle small = new Rectangle();
		small.width = 10;
		small.height = 5;
		Rectangle big = new Rectangle();
		big.width = 50;
		big.height = 20;

		System.out.println("Small:: width = " + small.width
					+ " height = " + small.height);
		System.out.println("small area = " + small.calculateArea());
		
		System.out.println("Big:: width = " + big.width
					+ " height = " + big.height);
		System.out.println("big area = " + big.calculateArea());
	}
}
