package com.techlabs.annotation;

import java.lang.annotation.*;
import java.lang.reflect.*;

public class Reflector {
	public static void showNeedToRefacorMethods(Class cls)
	{
		Method[] methods = cls.getMethods();
		for (Method m : methods)
		{
			Annotation[] annotations = m.getAnnotations();
			for (Annotation a : annotations)
			{
				if (a instanceof NeedToRefactor)
				{
					System.out.println("Needs to refactor: " + m.getName());
				}
			}
		}
	}
}
