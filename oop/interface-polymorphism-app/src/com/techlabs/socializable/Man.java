package com.techlabs.socializable;

public class Man implements ISocializable {

	@Override
	public void wish()
	{
		System.out.println("Man says hello");
	}

	@Override
	public void depart()
	{
		System.out.println("Man says bye");
	}

}
