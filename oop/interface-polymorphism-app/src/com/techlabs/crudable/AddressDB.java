package com.techlabs.crudable;

public class AddressDB implements ICRUDable {
	@Override
	public void create()
	{
		System.out.println("Address is creating");
	}

	@Override
	public void read()
	{
		System.out.println("Address is reading");
	}

	@Override
	public void update()
	{
		System.out.println("Address is updating");
	}

	@Override
	public void delete()
	{
		System.out.println("Address is deleting");
	}
}
