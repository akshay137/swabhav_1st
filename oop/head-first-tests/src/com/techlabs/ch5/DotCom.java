package com.techlabs.ch5;

import java.util.*;

public class DotCom {
	private ArrayList<String> locationCells;
	private String name;
	
	public void setLocations(ArrayList<String> locations)
	{
		this.locationCells = locations;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String checkYourself(String stringGuess)
	{
		String result = "miss";
		int index = locationCells.indexOf(stringGuess);
		if (index >= 0)
		{
			locationCells.remove(stringGuess);
			if (locationCells.isEmpty())
			{
				result = "kill";
				System.out.println("You sank " + name);
			}
			else
			{
				result = "hit";
			}
		}
		return result;
	}
}
