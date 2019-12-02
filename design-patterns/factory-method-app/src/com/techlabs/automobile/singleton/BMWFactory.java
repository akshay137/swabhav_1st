package com.techlabs.automobile.singleton;

public class BMWFactory implements IAutoFactory {

	private static BMWFactory instance;
	
	private BMWFactory() {}
	
	public static BMWFactory getInstance()
	{
		if (instance == null)
			instance = new BMWFactory();
		return instance;
	}
	
	@Override
	public IAutoMobile make() {
		return new BMW();
	}

}
