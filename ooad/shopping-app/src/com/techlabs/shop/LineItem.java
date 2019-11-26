package com.techlabs.shop;

public class LineItem {
	private int id;
	private Product product;
	private int quantity;
	
	public LineItem(int id, Product product, int quantity)
	{
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct()
	{
		return this.product;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
	
	public void setQuantity(int quantity)
	{
		if (quantity < 0)
			return;
		this.quantity = quantity;
	}
	
	public double calculateFinalPrice()
	{
		return this.product.calculatePrice() * this.quantity;
	}
	
	@Override
	public String toString()
	{
		return String.format("(%s x %d) @ $%.2f", this.product,
				this.quantity, calculateFinalPrice());
	}
	
	public boolean isProductSameAs(LineItem item)
	{
		return this.product.equals(item.getProduct());
	}
}
