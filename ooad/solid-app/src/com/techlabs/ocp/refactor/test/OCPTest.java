package com.techlabs.ocp.refactor.test;

import com.techlabs.ocp.refactor.*;

public class OCPTest {
	public static void main(String[] args)
	{
		FixedDeposit fd = new FixedDeposit("a", 100000.0, 10,
//				new NormalRate());
//				new HoliRate());
				new NewYearRate());
		System.out.printf("simple interest: %.2f\n",
				fd.calculateSimpleInterest());
	}
}
