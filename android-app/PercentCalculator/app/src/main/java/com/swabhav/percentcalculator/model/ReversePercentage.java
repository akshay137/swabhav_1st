package com.swabhav.percentcalculator.model;

public class ReversePercentage implements PercentageCalculator {

	@Override
	public double calculate(double x, double y) {
		return (x / y) * 100.0;
	}

	@Override
	public String toString() {
		return "X is what percent of Y";
	}
}
