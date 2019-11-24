package com.techlabs.ch5.test;

import java.io.*;
import java.util.*;

public class GameHelper {
	
	private static final String alphabet = "abcdefg";
	private final int gridLength = 7;
	private final int gridSize = gridLength * gridLength;
	private int[] grid = new int[gridSize];
	private int comCount = 0;
	
	public String getUserInput(String promt)
	{
		String inputLine = null;
		System.out.print(promt + " ");
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			inputLine = br.readLine();
			if (inputLine.length() == 0)
				return null;
		}
		catch (IOException ioe)
		{
			System.out.println("IOException: " + ioe);
		}
		return inputLine;
	}
	
	public ArrayList<String> placeDotCom(int size)
	{
		ArrayList<String> alphaCells = new ArrayList<String>();
		String[] alphaCoords = new String[size];
		String temp = null;
		int[] coords = new int[size];
		int attempts = 0;
		boolean success = false;
		int location = 0;
		
		comCount++;
		int incr = 1;
		if ((comCount % 2) == 1)
			incr = gridLength;
		
		while (!success & attempts++ < 200)
		{
			location = (int) (Math.random() * gridSize);
			int x = 0;
			success = true;
			while (success && x < size)
			{
				if (grid[location] == 0)
				{
					coords[x++] = location;
					location += incr;
					if (location >= gridSize)
						success = false;
					if (x > 0 && (location % gridLength == 0))
						success = false;
				}
				else
				{
					success = false;
				}
			}
		}
		int x = 0;
		int row = 0;
		int column = 0;
		while (x < size)
		{
			grid[coords[x]] = 1;
			row = (int)(coords[x] / gridLength);
			column = coords[x] % gridLength;
			temp = String.valueOf(alphabet.charAt(column));
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
//			System.out.println(x + " " + alphaCells.get(x - 1));
		}
		return alphaCells;
	}
	
}
