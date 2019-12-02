package com.techlabs.automobile;

public class AutoFactory {
	
	private static AutoFactory instance;
	
	private AutoFactory() {}
	
	public static AutoFactory getInstance()
	{
		if (instance == null)
			instance = new AutoFactory();
		return instance;
	}
	
	public IAutoMobile make(AutoMobileType type) {
		switch (type)
		{
		case BMW:
			return new BMW();
			
		case Audi:
			return new Audi();
			
		case Tesla:
			return new Tesla();
		}
		
		return null;
	}
}
