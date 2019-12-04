package com.techlabs.decorator;

public class BasicInspection implements IBikeService {
	
	private final double cost = 200.0;
	private IBikeService service;
	
	public BasicInspection()
	{
		this(null);
	}
	
	public BasicInspection(IBikeService service)
	{
		this.service = service;
	}

	@Override
	public double getCost() {
		return this.cost + (this.service == null ? 0 : this.service.getCost());
	}

	@Override
	public void printDescription() {
		System.out.printf("basic inspection @ %.2f\n", this.cost);
		if (this.service != null)
			this.service.printDescription();
	}

}
