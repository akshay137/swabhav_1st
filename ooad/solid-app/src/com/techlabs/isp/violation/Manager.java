package com.techlabs.isp.violation;

public class Manager implements IWork {

	@Override
	public void startWork() {
		System.out.println("manager starts working");
	}

	@Override
	public void stopWork() {
		System.out.println("manager stops working");
	}

	@Override
	public void startEat() {
		System.out.println("manager starts eating");
	}

	@Override
	public void stopEat() {
		System.out.println("manager stops eating");
	}

}
