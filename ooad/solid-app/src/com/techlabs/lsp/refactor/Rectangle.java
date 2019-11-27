package com.techlabs.lsp.refactor;

public class Rectangle implements Polygon {

	private int width;
	private int height;
	
	public Rectangle(int width, int height)
	{
		this.setWidth(width);
		this.setHeight(height);
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public void setWidth(int width)
	{
		if (width < 0)
			width = 1;
		this.width = width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public void setHeight(int height)
	{
		if (height < 0)
			height = 1;
		this.height = height;
	}
	
	@Override
	public int calculateArea()
	{
		return this.width * this.height;
	}

}
