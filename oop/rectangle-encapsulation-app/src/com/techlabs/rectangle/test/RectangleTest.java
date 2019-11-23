package com.techlabs.rectangle.test;
import com.techlabs.rectangle.*;

public class RectangleTest {
	public static void main(String[] args)
	{
		Rectangle small = new Rectangle();
		small.setWidth(-1);
		small.setHeight(500);
		System.out.println("small color: " + small.getColor());
		Rectangle big = new Rectangle();
		big.setWidth(50);
		big.setHeight(20);
		big.setColor(Rectangle.Colors.Blue);
		System.out.println("big color: " + big.getColor());

		System.out.println("Small:: width = " + small.getWidth()
					+ " height = " + small.getHeight());
		System.out.println("small area = " + small.calculateArea());
		
		System.out.println("Big:: width = " + big.getWidth()
					+ " height = " + big.getHeight());
		System.out.println("big area = " + big.calculateArea());
	}
}
