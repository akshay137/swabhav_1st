package com.techlabs.automobile;

public class BMWFactory implements IAutoFactory {

	@Override
	public IAutoMobile make() {
		return new BMW();
	}

}
