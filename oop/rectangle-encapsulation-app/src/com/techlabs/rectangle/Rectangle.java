package com.techlabs.rectangle;

public class Rectangle {
	private int width;
	private int height;
	private Colors color = Colors.Red;
	
	public enum Colors
	{
		Red, Green, Blue
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public void setWidth(int width)
	{
		this.width = clamp(width, 1, 100);
	}
	
	public void setHeight(int height)
	{
		
		this.height = clamp(height, 1, 100);
	}
	
	public Colors getColor()
	{
		return color;
	}
	
	public void setColor(Colors color)
	{
		this.color = color;
	}
	
	public int calculateArea()
	{
		return width * height;
	}
	
	private int clamp(int value, int min, int max)
	{
		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;
	}
}
