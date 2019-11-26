package com.techlabs.model;

public class LineItem {
	private int id;
	private String productName;
	private int quantity;
	private double price;
	
	public LineItem(int id, String productName, int quantity, double price)
	{
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getProductName()
	{
		return this.productName;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public double calculateFinalPrice()
	{
		return this.price * this.quantity;
	}
	
	@Override
	public int hashCode()
	{
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		LineItem item = (LineItem)obj;
		return (this.id == item.id) && (this.quantity == item.quantity)
				&& (this.price == item.price)
				&& (this.productName.equals(item.getProductName()));
	}
}
