package com.techlabs.automobile;

public class TeslaFactory implements IAutoFactory{

	@Override
	public IAutoMobile make() {
		return new Tesla();
	}

}
