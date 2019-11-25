package com.techlabs.crudable;

public class CustomerDB implements ICRUDable {

	@Override
	public void create()
	{
		System.out.println("Customer is creating");
	}

	@Override
	public void read()
	{
		System.out.println("Customer is reading");
	}

	@Override
	public void update()
	{
		System.out.println("Customer is updating");
	}

	@Override
	public void delete()
	{
		System.out.println("Customer is deleting");
	}

}
