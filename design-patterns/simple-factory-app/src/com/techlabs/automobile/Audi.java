package com.techlabs.automobile;

class Audi implements IAutoMobile {

	@Override
	public void start() {
		System.out.printf("%s is starting\n", this.getClass().getSimpleName());
	}

	@Override
	public void stop() {
		System.out.printf("%s is starting\n", this.getClass().getSimpleName());
	}

}
