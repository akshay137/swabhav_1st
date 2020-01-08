package com.swabhav.percentcalculator.model;

public abstract class PercentageCalculator {
	private double x;
	private double y;

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public abstract double calculate();
}
