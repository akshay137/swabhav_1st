package com.techlabs.ch2;

public class Remote {
	private DogDoor door;
	
	public Remote(DogDoor door)
	{
		this.door = door;
	}
	
	public void pressButton()
	{
		if (door.isOpen())
			door.close();
		else
			door.open();
	}
	
	public DogDoor getDoor()
	{
		return this.door;
	}
}
