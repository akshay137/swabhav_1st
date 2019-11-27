package com.techlabs.dip.test;

import com.techlabs.dip.refactor.*;

public class DIPRefactorTest {
	public static void main(String[] args)
	{
		TaxCalculator tc = new TaxCalculator(new XMLLogger());
		System.out.println(tc.calculateTax(1,  0));
		tc.setLogger(new TextLogger());
		System.out.println(tc.calculateTax(1,  0));
	}
}
