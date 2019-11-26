package com.techlabs.srp.violation;

public class Invoice {
	private int id;
	private String name;
	private double cost;
	private float discountPercentage;
	private float tax;
	
	private static int idCounter = 0;
	
	public Invoice(String name, double cost, float discount, float tax)
	{
		this.id = idCounter++;
		this.name = name;
		this.setCost(cost);
		this.setDiscount(discount);
		this.setTax(tax);
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
	
	public void setCost(double cost)
	{
		if (this.cost < 0.0)
			return;
		this.cost = cost;
	}
	
	public float getDiscount()
	{
		return this.discountPercentage;
	}
	
	public void setDiscount(float discount)
	{
		if (discount < 0.0f || discount > 100.0f)
			return;
		this.discountPercentage = discount;
	}
	
	public float getTax()
	{
		return this.tax;
	}
	
	public void setTax(float tax)
	{
		if (tax < 0.0f || tax > 1.0f)
			return;
		this.tax = tax;
	}
	
	public double calculateDiscount()
	{
		return this.cost - (this.cost * (this.discountPercentage / 100.0f));
	}
	
	public double calculateTax()
	{
		return this.cost * this.tax;
	}
	
	public double calculateTotalCost()
	{
		final double discountPrice = calculateDiscount();
		final double taxOnPrice = calculateTax();
		return discountPrice + taxOnPrice;
	}
	
	public void printDetails()
	{
		System.out.printf("Invoice ID: %d\n", this.id);
		System.out.printf("cost: %.2f\n", this.cost);
		System.out.printf("discount: %.2f\n", this.discountPercentage);
		System.out.printf("tax: %.2f\n", calculateTax());
		System.out.printf("Final Cost: %.2f\n", calculateTotalCost());
	}
}
