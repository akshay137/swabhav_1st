package com.techlabs.srp.refactor.test;

import com.techlabs.srp.refactor.*;

public class InvoiceTest {
	public static void main(String[] args)
	{
		Invoice invoice = new Invoice("refactored", 1000.0, 40.0f, 0.3f);
		InvoicePrinter iPrinter = new InvoicePrinter(invoice);
		iPrinter.printToConsole();
	}
}
