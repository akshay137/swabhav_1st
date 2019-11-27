package com.techlabs.dip.refactor;

public class XMLLogger implements Logger {

	@Override
	public void log(String msg) {
		System.out.println("Logging to xml file: " + msg);
	}

}
