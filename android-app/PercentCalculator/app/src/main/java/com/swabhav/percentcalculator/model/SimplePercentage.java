package com.swabhav.percentcalculator.model;

public class SimplePercentage implements PercentageCalculator {

	@Override
	public double calculate(double x, double y) {
		return y * (x / 100.0);
	}

	@Override
	public String toString() {
		return "X percent of Y";
	}
}
