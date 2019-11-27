package com.techlabs.lsp.test;

import com.techlabs.lsp.violation.*;

public class LSPRefactorTest {
	public static void main(String[] args)
	{
		Rectangle r = new Rectangle(100, 5);
		Square s = new Square(100);
		testShape(r);
		testShape(s);
	}
	
	private static void testShape(Rectangle r)
	{
		int before = r.getWidth();
		r.setHeight(20);
		int after = r.getWidth();
		System.out.printf("%s: width test: %b\n", r, (before == after));
	}
}
