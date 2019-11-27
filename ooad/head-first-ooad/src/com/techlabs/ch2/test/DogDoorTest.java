package com.techlabs.ch2.test;

import com.techlabs.ch2.*;

public class DogDoorTest {
	public static void main(String[] args)
	{
		DogDoor door = new DogDoor();
		Remote remote = new Remote(door);
		System.out.println("Fido barks to go outside");
		remote.pressButton();
		System.out.println("Fido is outside");
		System.out.println("Fido is all done");
		try
		{
//			Thread.currentThread();
			Thread.sleep(10000);
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
		System.out.println("Fido scratches door");
		remote.pressButton();
		System.out.println("Fido's back inside");
	}
}
