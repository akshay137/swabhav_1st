package com.techlabs.reflector;

import java.lang.reflect.*;

public class Reflector {
	public static void printClassInfo(Class cls)
	{
		System.out.println("class name " + cls.getName());
		Method[] methods = cls.getMethods();
		System.out.println("total methods " + methods.length);
		int getters = 0;
		int setters = 0;
		for (Method m : methods)
		{
			String name = m.getName();
			if (name.startsWith("get"))
				getters++;
			else if (name.startsWith("set"))
				setters++;
			System.out.println(Modifier.toString(m.getModifiers()) + " " + name);
		}
		System.out.println("getters: " + getters);
		System.out.println("setters: " + setters);
		Constructor[] constructors = cls.getDeclaredConstructors();
		System.out.println("constructors " + constructors.length);
	}
}
