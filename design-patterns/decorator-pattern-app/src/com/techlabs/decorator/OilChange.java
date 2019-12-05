package com.techlabs.decorator;

public class OilChange implements IBikeService {
	
	private final double cost = 500.0;
	private IBikeService service;
	
	public OilChange()
	{
		this(null);
	}
	
	public OilChange(IBikeService service)
	{
		this.service = service;
	}

	@Override
	public double getCost() {
		return this.cost + (this.service == null ? 0 : this.service.getCost());
	}

	public void printDescription() {
		System.out.printf("oil change @ %.2f\n", this.cost);
	}

	@Override
	public String getDescription() {
		return String.format("oil change @ %.2f\n%s", this.cost,
				(this.service == null ? "" : this.service.getDescription()));
	}

}
