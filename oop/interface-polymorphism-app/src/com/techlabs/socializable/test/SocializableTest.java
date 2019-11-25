package com.techlabs.socializable.test;

import com.techlabs.socializable.*;

public class SocializableTest {
	private static void atTheParty(ISocializable obj)
	{
		obj.wish();
		obj.depart();
	}
	
	private static void atTheCinemas(IEmotional obj)
	{
		obj.cry();
		obj.laugh();
	}
	
	public static void main(String[] args)
	{
		Boy boy = new Boy();
		Man man = new Man();
		atTheParty(boy);
		atTheParty(man);
		atTheCinemas(boy);
//		atTheCinemas(man);
	}
}
