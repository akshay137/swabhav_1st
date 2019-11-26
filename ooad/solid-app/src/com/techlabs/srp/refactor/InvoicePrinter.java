package com.techlabs.srp.refactor;

public class InvoicePrinter {
	private Invoice invoice;
	
	public InvoicePrinter(Invoice invoice)
	{
		this.invoice = invoice;
	}
	
	public void printToConsole()
	{
		System.out.printf("Invoice ID: %d\n", this.invoice.getId());
		System.out.printf("cost: %.2f\n", this.invoice.getCost());
		System.out.printf("discount: %.2f\n",
					this.invoice.getDiscount());
		System.out.printf("tax: %.2f\n", this.invoice.calculateTax());
		System.out.printf("Final Cost: %.2f\n",
				this.invoice.calculateTotalCost());
	}

}
