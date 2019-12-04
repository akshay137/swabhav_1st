package com.techlabs.decorator;

public class TyreRotation implements IBikeService {
	
	private final double cost = 800.0;
	private IBikeService service;
	
	public TyreRotation()
	{
		this(null);
	}
	
	public TyreRotation(IBikeService service)
	{
		this.service = service;
	}

	@Override
	public double getCost() {
		return this.cost + (this.service == null ? 0 : this.service.getCost());
	}

	@Override
	public void printDescription() {
		System.out.printf("tyre rotation @ %.2f\n", this.cost);
		if (this.service != null)
			this.service.printDescription();
	}

}
