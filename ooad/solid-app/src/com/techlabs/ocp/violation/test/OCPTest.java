package com.techlabs.ocp.violation.test;

import com.techlabs.ocp.*;
import com.techlabs.ocp.violation.*;

public class OCPTest {
	public static void main(String[] args)
	{
		FixedDeposit fd = new FixedDeposit("a", 100000.0, 10, Festival.HOLI);
		System.out.printf("simple interest: %.2f\n",
				fd.calculateSimpleInterest());
	}
}
