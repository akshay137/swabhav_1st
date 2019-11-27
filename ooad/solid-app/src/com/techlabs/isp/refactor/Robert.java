package com.techlabs.isp.refactor;

public class Robert implements IWork {
	@Override
	public void startWork() {
		System.out.println("robert starts working");
	}

	@Override
	public void stopWork() {
		System.out.println("robert stops working");
	}
}
