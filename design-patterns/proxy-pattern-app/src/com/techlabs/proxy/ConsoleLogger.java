package com.techlabs.proxy;

import java.util.*;

public class ConsoleLogger implements ILogger {

	@Override
	public void log(String message) {
		System.out.printf("%s [%tc]\n", message, new Date());
	}

}
