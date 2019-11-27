package com.techlabs.ch1;

import java.util.*;

public class Inventory {
	private ArrayList<Guitar> guitarList;
	
	public Inventory()
	{
		guitarList = new ArrayList<Guitar>();
	}
	
	public void addGuitar(Guitar guitar)
	{
		guitarList.add(guitar);
	}
	
	public Guitar getGuitar()
	{
		return null;
	}
	
	public ArrayList<Guitar> search(GuitarSpec specs)
	{
		ArrayList<Guitar> matches = new ArrayList<Guitar>();
		for (Iterator<Guitar> i = guitarList.iterator(); i.hasNext();)
		{
			Guitar guitar = (Guitar)i.next();
			if (guitar.getSpecs().equals(specs))
			{
				matches.add(guitar);
			}
		}
		return matches;
	}
}
