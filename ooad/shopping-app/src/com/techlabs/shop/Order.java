package com.techlabs.shop;

import java.util.*;

public class Order {
	private int id;
	private Date date;
	private ArrayList<LineItem> items;
	
	public Order(int id, Date date)
	{
		this.id = id;
		this.date = date;
		items = new ArrayList<LineItem>();
	}
	
	public Date getDate()
	{
		return this.date;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void addLineItem(LineItem item)
	{
		for (LineItem lItem : items)
		{
			if (item.isProductSameAs(lItem))
			{
				lItem.setQuantity(lItem.getQuantity() + item.getQuantity());
				return;
			}
		}
		items.add(item);
	}
	
	public double checkout()
	{
		double finalPrice = 0.0;
		for (LineItem item : items)
			finalPrice += item.calculateFinalPrice();
		return finalPrice;
	}
	
	public List<LineItem> getItems()
	{
		return (List<LineItem>)this.items;
	}
}
