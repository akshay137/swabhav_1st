package com.techlabs.shop.test;

import java.util.*;
import com.techlabs.shop.*;

public class ShopTest {
	public static void main(String[] args)
	{
		Customer me = new Customer(0, "me");
		setUpOrder(me);
//		setUpOrder(me);
//		setUpOrder(me);
		printInvoice(me);
	}
	
	private static void setUpOrder(Customer customer)
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, (int)(Math.random() * 28));
		c.set(Calendar.MONTH, (int)(Math.random() * 12));
		c.set(Calendar.YEAR, 2016 + (int)(Math.random() * 3));
		Order order = new Order((int)(Math.random() * 10), c.getTime());
		order.addLineItem(new LineItem(0,
				new Product(0, "book-1", 50.0, Math.random()),
				1 +(int)(Math.random() * 10)));
		order.addLineItem(new LineItem(0,
				new Product(1, "book-2", 50.0, Math.random()),
				1 +(int)(Math.random() * 10)));
		order.addLineItem(new LineItem(0,
				new Product(2, "book-3", 50.0, Math.random()),
				1 +(int)(Math.random() * 10)));
		order.addLineItem(new LineItem(0,
				new Product(3, "book-4", 50.0, Math.random()),
				1 +(int)(Math.random() * 10)));
		order.addLineItem(new LineItem(4,
				new Product(4, "book-5", 50.0, 0.2), 5));
		order.addLineItem(new LineItem(5,
				new Product(4, "book-5", 50.0, 0.2), 4));
		order.addLineItem(new LineItem(6,
				new Product(5, "book-5", 50.0, 0.3), 6));
		customer.addOrder(order);
	}
	
	private static void printInvoice(Customer customer)
	{
		System.out.printf("Invoice for: %s\n", customer.getName());
		for (Order order : customer.getOrders())
		{
			System.out.printf("Date: %s\n", order.getDate());
			for (LineItem item : order.getItems())
			{
				System.out.println(item);
			}
			System.out.printf("total: $%.2f\n", order.checkout());
			System.out.println();	
		}
	}
}
