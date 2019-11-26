package com.techlabs.ch1;

public class GuitarSpec {
	private String model;
	private GuitarBuilder builder;
	private GuitarType type;
	private GuitarWood topWood;
	private GuitarWood backWood;
	private int numStrings;
	
	public GuitarSpec(String model, GuitarBuilder builder, GuitarType type,
			GuitarWood topWood, GuitarWood backWood, int numStrings)
	{
		this.model = model;
		this.builder = builder;
		this.type = type;
		this.topWood = topWood;
		this.backWood = backWood;
		this.numStrings = numStrings;
	}
	
	public String getModel()
	{
		return this.model;
	}
	
	public GuitarBuilder getBuilder()
	{
		return this.builder;
	}
	
	public GuitarType getType()
	{
		return this.type;
	}
	
	public GuitarWood getTopWood()
	{
		return this.topWood;
	}
	
	public GuitarWood getBackWood()
	{
		return this.backWood;
	}
	
	public int getNumStrings()
	{
		return this.numStrings;
	}
	
	@Override
	public boolean equals(Object guitarSpecs)
	{
		GuitarSpec specs = (GuitarSpec)guitarSpecs;
		return (this.numStrings == specs.getNumStrings())
				&& (this.builder == specs.builder)
				&& (this.type == specs.type)
				&& (this.topWood == specs.topWood)
				&& (this.backWood == specs.backWood)
				&& (this.model.toLowerCase().equals(specs.getModel().toLowerCase()));
	}
	
	@Override
	public String toString()
	{
		return String.format("%s %s %d-string %s guitar:",
				this.builder, this.model, this.numStrings, this.type);
	}
}
