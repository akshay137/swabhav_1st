package com.swabhav.percentcalculator.model;

public class SimplePercentage extends PercentageCalculator {

	@Override
	public double calculate() {
		return this.getY() * (this.getX() / 100.0);
	}

	@Override
	public String toString() {
		return "X percent of Y";
	}
}
