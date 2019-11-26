package com.techlabs.srp.violation.test;

import com.techlabs.srp.violation.*;

public class InvoiceTest {
	public static void main(String[] args)
	{
		Invoice ivc = new Invoice("a", 500.0, 25.0f, 0.1f);
		ivc.printDetails();
	}
}
