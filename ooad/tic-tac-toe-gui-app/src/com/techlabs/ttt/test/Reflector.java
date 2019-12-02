package com.techlabs.ttt.test;

import java.lang.reflect.*;

public class Reflector {
    
    public static Method getMethod(Class<?> cls, String methodName,
	    int parameterCount)
    {
	Method[] methods = cls.getDeclaredMethods();
	for (Method method : methods)
	{
	    if (method.getName().equals(methodName)
		    && method.getParameterCount() == parameterCount)
	    {
		method.setAccessible(true);
		return method;
	    }
	}
	return null;
    }
}
