package com.techlabs.ch2.test;

import com.techlabs.ch2.*;

public class DogDoorTest {
	public static void main(String[] args)
	{
		DogDoor door = new DogDoor();
		Remote remote = new Remote(door);
		System.out.println("Fido barks to go outside");
		remote.pressButton();
		System.out.println("\nFido is outside");
		remote.pressButton();
		System.out.println("\nFido is all done");
		remote.pressButton();
		System.out.println("\nFido's back inside");
		remote.pressButton();
	}
}
