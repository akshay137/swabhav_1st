package com.techlabs.socializable;

public class Boy implements ISocializable, IEmotional {

	@Override
	public void wish()
	{
		System.out.println("Boy says hello");
	}

	@Override
	public void depart()
	{
		System.out.println("Boy says bye");
	}
	
	@Override
	public void laugh()
	{
		System.out.println("Boy laughs");
	}
	
	@Override
	public void cry()
	{
		System.out.println("Boy cries");
	}

}
