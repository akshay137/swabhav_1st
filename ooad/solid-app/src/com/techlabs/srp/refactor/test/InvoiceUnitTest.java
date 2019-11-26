package com.techlabs.srp.refactor.test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.techlabs.srp.refactor.*;

public class InvoiceUnitTest {
	
	@Test
	void test()
	{
		Invoice invoice = new Invoice("a", 1000.0, 2.0f, 1.0f);
		assertTrue(1000.0 == invoice.getCost());
		assertTrue(1.0 == invoice.getCost());
	}
}
