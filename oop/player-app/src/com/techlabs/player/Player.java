package com.techlabs.player;

public class Player {
	private int id;
	private String name;
	private int age;
	
	private static int count = 0;
	
	public Player(int id, String name, int age)
	{
		this.id = id;
		this.name = name;
		this.age = age;
		count++;
	}
	
	public Player(int id, String name)
	{
		this(id, name, 18);
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public Player whoIsElder(Player other)
	{
		return this.age < other.age ? other : this;
	}
	
	public static int getInstanceCount()
	{
		return count;
	}
}
