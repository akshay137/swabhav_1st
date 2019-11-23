package com.techlabs.player.test;

import com.techlabs.player.*;

public class PlayerTest {
	public static void main(String[] args)
	{
		Player mario = new Player(1, "Mario", 24);
		Player luigi = new Player(2, "Luigi");
		
		System.out.println("Elder one is " + mario.whoIsElder(luigi).getName());
		System.out.println("Instances " + Player.getInstanceCount());
	}
}
