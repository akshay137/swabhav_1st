package com.swabhav.percentcalculator.model;

public class PercentDifference extends PercentageCalculator {

	@Override
	public double calculate() {
		return -(1.0 - (this.getY() / this.getX())) * 100.0;
	}

	@Override
	public String toString() {
		return "X is % increase/decrease from Y";
	}
}
