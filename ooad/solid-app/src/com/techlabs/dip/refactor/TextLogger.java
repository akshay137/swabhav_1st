package com.techlabs.dip.refactor;

public class TextLogger implements Logger {

	@Override
	public void log(String msg) {
		System.out.println("Logging to text file: " + msg);
	}

}
