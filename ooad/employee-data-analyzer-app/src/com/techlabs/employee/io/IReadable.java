package com.techlabs.employee.io;

public interface IReadable {
	String readFile(String path);
	byte[] readFileBytes(String path);
}
