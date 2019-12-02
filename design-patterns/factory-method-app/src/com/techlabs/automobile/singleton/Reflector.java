package com.techlabs.automobile.singleton;

import java.lang.reflect.*;

public class Reflector {
	public static Object invokeStaticMethod(Class<?> cls, String methodName,
			Object ...objects)
	{
		return invokeMethod(cls, null, methodName, objects);
	}
	
	public static Object invokeMethod(Class<?> cls, Object instance,
			String methodName, Object ...objects)
	{
		Method method = getMethod(cls, methodName, objects.length);
		try
		{
			return method.invoke(instance, objects);
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
		return null;
	}
	
	public static Class<?> getClass(String className)
	{
		if (className == null)
			return null;
		try
		{
			Class<?> cls = Class.forName(className);
			return cls;
		}
		catch (ClassNotFoundException exception)
		{
			System.out.println(exception.getMessage());
			return null;
		}
	}
	
	public static Method getMethod(Class<?> cls,
			String methodName, int parameterCount)
	{
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods)
		{
			if (method.getName().equals(methodName)
					&& method.getParameterCount() == parameterCount)
			{
				return method;
			}
		}
		return null;
	}
}
