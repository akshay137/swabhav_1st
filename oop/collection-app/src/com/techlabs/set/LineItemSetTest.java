package com.techlabs.set;

import java.util.*;
import com.techlabs.model.*;

public class LineItemSetTest {
	public static void main(String[] main)
	{
		HashSet<LineItem> orderSet = new HashSet<LineItem>();
		orderSet.add(new LineItem(1, "book-2", 5, 50.0));
		orderSet.add(new LineItem(1, "book-2", 5, 50.0));
		System.out.printf("orderSet size: %d\n", orderSet.size());
	}
}
