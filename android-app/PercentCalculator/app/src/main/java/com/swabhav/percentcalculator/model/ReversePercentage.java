package com.swabhav.percentcalculator.model;

public class ReversePercentage extends PercentageCalculator {

	@Override
	public double calculate() {
		return (this.getX() / this.getY()) * 100.0;
	}

	@Override
	public String toString() {
		return "X is what percent of Y";
	}
}
