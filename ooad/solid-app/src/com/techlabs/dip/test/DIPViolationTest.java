package com.techlabs.dip.test;

import com.techlabs.dip.violation.*;

public class DIPViolationTest {
	public static void main(String[] args)
	{
		TaxCalculator tc = new TaxCalculator(0);
		System.out.println(tc.calculateTax(1,  0));
		tc.setLogType(1);
		System.out.println(tc.calculateTax(1,  0));
	}
}
