package com.techlabs;

public class PassByValueTest {
	
	public static void main(String[] args)
	{
		int mark = 100;
		System.out.println(mark);
		mutateMarkToZero(mark);
		System.out.println(mark);
		
		int marks[] = {10, 20, 30};
		for(int m : marks)
			System.out.println(m);
		mutateMarksToZero(marks);
		for(int m : marks)
			System.out.println(m);
		
		System.out.println(marks);
		System.out.println(marks.hashCode());
	}
	
	private static void mutateMarkToZero(int mark)
	{
		mark = 0;
	}
	
	private static void mutateMarksToZero(int[] marks)
	{
		for (int i = 0; i < marks.length; i++)
			marks[i] = 0;
		System.out.println(marks.hashCode());
	}

}
