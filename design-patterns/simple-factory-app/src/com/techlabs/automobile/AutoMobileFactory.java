package com.techlabs.automobile;

public class AutoMobileFactory {
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
