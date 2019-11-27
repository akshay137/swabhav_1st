package com.techlabs.isp.test;

import com.techlabs.isp.refactor.*;;

public class ISPRefactorTest {

	public static void main(String[] args) {
		Manager manager = new Manager();
		Robert robert = new Robert();
		atTheWork(manager);
		atTheWork(robert);
		atTheCafe(manager);
//		atTheCafe(robert);
	}
	
	private static void atTheCafe(IEat w)
	{
		w.startEat();
		w.stopEat();
	}
	
	private static void atTheWork(IWork w)
	{
		w.startWork();
		w.stopWork();
	}

}
