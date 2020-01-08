package com.swabhav.percentcalculator.model;

public class PercentDifference implements PercentageCalculator {

	@Override
	public double calculate(double x, double y) {
		return -(1.0 - (y / x)) * 100.0;
	}

	@Override
	public String toString() {
		return "Y is % increase/decrease from X";
	}
}
