package com.techlabs.shop;

import java.util.*;

public class Customer {
	private int id;
	private String name;
	private ArrayList<Order> orders;
	
	public Customer(int id, String name)
	{
		this.id = id;
		this.name = name;
		orders = new ArrayList<Order>();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public boolean addOrder(Order order)
	{
		return orders.add(order);
	}
	
	public List<Order> getOrders()
	{
		return (List<Order>)orders;
	}
}
