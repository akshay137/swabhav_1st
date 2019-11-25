package com.techlabs.crudable.test;

import com.techlabs.crudable.*;

public class CRUDableTest {
	public static void main(String[] args)
	{
		doDBOperations(new CustomerDB());
		doDBOperations(new InvoiceDB());
		doDBOperations(new AddressDB());
	}
	
	private static void doDBOperations(ICRUDable db)
	{
		db.create();
		db.read();
		db.update();
		db.delete();
	}
}
