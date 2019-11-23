package com.techlabs.polymorphing;

public class Boy extends Man {
	@Override
	public void play()
	{
		System.out.println("Boy is playing");
	}
	
	public void walk()
	{
		System.out.println("Boy is walking");
	}
}
