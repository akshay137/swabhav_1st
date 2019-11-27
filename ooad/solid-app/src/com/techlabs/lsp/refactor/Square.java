package com.techlabs.lsp.refactor;

public class Square implements Polygon {
	
	private int side;
	
	public Square(int side)
	{
		this.setSide(side);
	}
	
	public int getSide()
	{
		return this.side;
	}
	
	public void setSide(int side)
	{
		if (side < 0)
			side = 1;
		this.side = side;
	}
	
	@Override
	public int calculateArea()
	{
		return side * side;
	}

}
