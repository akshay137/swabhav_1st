package com.techlabs.testcase;

import java.lang.reflect.*;
import java.lang.annotation.*;


public class Reflector {
	public static void checkTestCases(Class cls)
	{
		Method[] methods = cls.getMethods();
		int totalTestCaseMethods = 0;
		int testPassed = 0;
		for (Method method : methods)
		{
			Annotation[] annotations = method.getAnnotations();
			for (Annotation annotation : annotations)
			{
				if (annotation instanceof TestCase)
				{
					totalTestCaseMethods++;
//					Class retType = method.getReturnType();
					try
					{
						Object retValue = method.invoke(cls.newInstance());
//						System.out.println(retValue);
						if ((boolean)retValue)
						{
							testPassed++;
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		System.out.println("Total methods with test case " + totalTestCaseMethods);
		System.out.println("methods that passed test " + testPassed);
		System.out.println("methods that failed test "
				+ (totalTestCaseMethods - testPassed));
	}
}
