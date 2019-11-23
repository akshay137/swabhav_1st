package com.techlabs.person;

public class Person {
	private String name;
	private int age;
	private float height;
	private float weight;
	private GenderType gender;
	
	public Person(String name, int age)
	{
		this(name, age, 5.0f, 50.0f, GenderType.Male);
	}
	
	public Person(String name, int age, float height, float weight, GenderType gender)
	{
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public float getHeight()
	{
		return this.height;
	}
	
	public float getWeight()
	{
		return this.weight;
	}
	
	public GenderType getGender()
	{
		return this.gender;
	}
	
	public void workout()
	{
		this.weight -= this.weight * 0.02f;
	}
	
	public void eat()
	{
		this.weight += this.weight * 0.01f;
		this.height += this.height * 0.0025f;
	}
	
	public float calculateBMI()
	{
		float heightInMeters = this.height * 0.3048f;
		return this.weight / (heightInMeters * heightInMeters);
	}
	
	public BMICategory getBMICategory()
	{
		float bmi = calculateBMI();
		if (bmi < 18.5f)
			return BMICategory.UnderWieght;
		if (bmi < 25.0f)
			return BMICategory.Healthy;
		if (bmi < 30.0f)
			return BMICategory.OverWeight;
		return BMICategory.Obese;
	}
}
