package com.techlabs.collection.test;

import java.util.*;
import com.techlabs.model.*;

public class ArrayListTest {
	public static void main(String[] args)
	{
		ArrayList orders = new ArrayList();
		orders.add(new LineItem(0, "book-1", 5, 50.0));
		orders.add(new LineItem(1, "book-2", 5, 50.0));
		orders.add(new LineItem(2, "book-3", 5, 50.0));
		orders.add(new LineItem(3, "book-4", 5, 50.0));
		orders.add("string");
		
		for (Object item : orders)
		{
			System.out.printf("%s x %d = %.2f\n",
					((LineItem)item).getProductName(),
					((LineItem)item).getQuantity(),
					((LineItem)item).calculateFinalPrice());
		}
	}
}
