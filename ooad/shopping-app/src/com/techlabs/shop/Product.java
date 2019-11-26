package com.techlabs.shop;

public class Product {
	private int id;
	private String name;
	private double cost;
	private double discount;
	
	public Product(int id, String name, double cost, double discount)
	{
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.discount = discount;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getCost()
	{
		return this.cost;
	}
	
	public double getDiscount()
	{
		return this.discount;
	}
	
	public void setCost(double cost)
	{
		if (cost < 0.0)
			return;
		this.cost = cost;
	}
	
	public void setDiscount(double discount)
	{
		if (discount < 0.0 || discount > 1.0)
			return;
		this.discount = discount;
	}
	
	public double calculatePrice()
	{
		return this.cost * this.discount;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s @ $%3.2f [%2d%% off]",
				this.name, calculatePrice(), (int)(this.discount * 100));
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Product product = (Product)obj;
		return (this.id == product.getId());
	}
}
