package com.techlabs.crudable;

public class InvoiceDB implements ICRUDable {

	@Override
	public void create()
	{
		System.out.println("Invoice is creating");
	}

	@Override
	public void read()
	{
		System.out.println("Invoice is reading");
	}

	@Override
	public void update()
	{
		System.out.println("Invoice is updating");
	}

	@Override
	public void delete()
	{
		System.out.println("Invoice is deleting");
	}

}
