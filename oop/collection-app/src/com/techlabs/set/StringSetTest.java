package com.techlabs.set;

import java.util.*;

public class StringSetTest {
	public static void main(String[] args)
	{
		HashSet<String> stringSet = new HashSet<String>();
		stringSet.add("hello");
		stringSet.add("hello");
		stringSet.add("bye");
		System.out.printf("stringSet size: %d\n", stringSet.size());
	}
}
