package com.techlabs.automobile.singleton;

public class AudiFactory implements IAutoFactory {

	private static AudiFactory instance;
	
	private AudiFactory() {}
	
	public static AudiFactory getInstance()
	{
		if (instance == null)
			instance = new AudiFactory();
		return instance;
	}
	@Override
	public IAutoMobile make() {
		return new Audi();
	}

}
