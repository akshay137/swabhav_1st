package com.swabhav.mvvmtest;

public class User {
	private String name;

	public User() {
		this("abc");
	}

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
