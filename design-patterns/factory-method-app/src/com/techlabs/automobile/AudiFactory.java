package com.techlabs.automobile;

public class AudiFactory implements IAutoFactory {

	@Override
	public IAutoMobile make() {
		return new Audi();
	}

}
