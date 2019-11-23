package com.techlabs.rectangle;

public class Rectangle {
	public int width;
	public int height;
	
	public Rectangle()
	{
		this.width = 0;
		this.height = 0;
	}
	
	
	public int calculateArea()
	{
		return width * height;
	}
}
