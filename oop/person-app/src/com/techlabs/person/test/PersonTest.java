package com.techlabs.person.test;

import com.techlabs.person.*;

public class PersonTest {
	public static void main(String[] args)
	{
		Person p1 = new Person("Aladin", 24);
		Person p2 = new Person("Sultan", 23, .5f, 80, GenderType.Male);
		System.out.println("p1 weight = " + p1.getWeight());
		p1.workout();
		System.out.println("p1 weight aw = " + p1.getWeight());
		System.out.println("p1 height = " + p1.getHeight());
		p1.eat();
		System.out.println("p1 weight ae = " + p1.getWeight());
		System.out.println("p1 height ae = " + p1.getHeight());
		System.out.println("p1 is " + p1.getBMICategory());
		
		System.out.println();
		
		System.out.println("p2 weight = " + p2.getWeight());
		p2.workout();
		System.out.println("p2 weight aw = " + p2.getWeight());
		System.out.println("p2 height = " + p2.getHeight());
		p2.eat();
		System.out.println("p2 weight ae = " + p2.getWeight());
		System.out.println("p2 height ae = " + p2.getHeight());
		System.out.println("p2 is " + p2.getBMICategory());
	}
}
