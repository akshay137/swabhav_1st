package com.techlabs.decorator;

public class BasicInspection implements IBikeService {
	
	private final double cost = 200.0;
	
	public BasicInspection() {}
	
	@Override
	public double getCost() {
		return this.cost;
	}

	public void printDescription() {
		System.out.printf("basic inspection @ %.2f\n", this.cost);
	}

	@Override
	public String getDescription() {
		return String.format("basic inspection @ %.2f\n", this.cost);
	}

}
