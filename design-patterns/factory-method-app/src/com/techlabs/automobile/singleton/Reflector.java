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
		Method method = getMethod(cls, methodName, objects);
		method.setAccessible(true);
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
			String methodName, Object... parameters)
	{
		Class<?> paramterCls[] = new Class[parameters.length];
		for (int i = 0; i < parameters.length; i++) 
			paramterCls[i] = parameters[i].getClass();
		
		try {
			return cls.getDeclaredMethod(methodName, paramterCls);
		}
		catch (NoSuchMethodException | SecurityException exception) {
			System.out.println(exception.getMessage());
			return null;
		}
	}
}
