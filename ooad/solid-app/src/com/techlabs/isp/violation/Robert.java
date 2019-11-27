package com.techlabs.isp.violation;

public class Robert implements IWork {
	@Override
	public void startWork() {
		System.out.println("robert starts working");
	}

	@Override
	public void stopWork() {
		System.out.println("robert stops working");
	}

	@Override
	public void startEat() {
		throw new RuntimeException();
	}

	@Override
	public void stopEat() {
		throw new RuntimeException();
	}
}
