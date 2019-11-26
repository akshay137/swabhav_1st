package com.techlabs.ch1.test;

import java.util.*;
import com.techlabs.ch1.*;

public class FindGuirtarTester {
	public static void main(String[] args)
	{
		Inventory inventory = new Inventory();
		setUpInventory(inventory);
		
		GuitarSpec whatErinLikes = new GuitarSpec("Stratocaster", GuitarBuilder.FENDER,
				GuitarType.ELECTRIC, GuitarWood.ALDER, GuitarWood.ALDER, 6);
		ArrayList<Guitar> found = inventory.search(whatErinLikes);
		
		System.out.println("Erin you might like these guitars");
		printGuitarList(found);
	}
	
	private static void printGuitarList(ArrayList<Guitar> gList)
	{
		for (Guitar guitar : gList)
		{
			GuitarSpec specs = guitar.getSpecs();
			System.out.printf("We have a %s\n", specs);
			System.out.printf("\t %s back and sides\n", specs.getBackWood());
			System.out.printf("\t %s top\n", specs.getTopWood());
			System.out.printf("You can have it for $%.2f\n", guitar.getPrice());
			System.out.println();
		}
	}
	
	private static void setUpInventory(Inventory inventory)
	{
		Guitar guitar = new Guitar(new GuitarSpec("Stratocaster",
				GuitarBuilder.FENDER, GuitarType.ELECTRIC,
				GuitarWood.ALDER, GuitarWood.ALDER, 6), "001", 1499.95);
		inventory.addGuitar(guitar);
		guitar = new Guitar(new GuitarSpec("Stratocaster",
				GuitarBuilder.FENDER, GuitarType.ELECTRIC,
				GuitarWood.ALDER, GuitarWood.ALDER, 6), "001", 1549.95);
		inventory.addGuitar(guitar);
		guitar = new Guitar(new GuitarSpec("something",
				GuitarBuilder.FENDER, GuitarType.ACCOUSTIC,
				GuitarWood.ALDER, GuitarWood.INDIAN_ROSEWOOD, 6), "001", 1049.95);
		inventory.addGuitar(guitar);
		guitar = new Guitar(new GuitarSpec("weird",
				GuitarBuilder.FENDER, GuitarType.ELECTRIC,
				GuitarWood.MAHOGANY, GuitarWood.MAPLE, 6), "001", 549.95);
		inventory.addGuitar(guitar);
		guitar = new Guitar(new GuitarSpec("Stratocaster",
				GuitarBuilder.PRS, GuitarType.ELECTRIC,
				GuitarWood.ALDER, GuitarWood.ALDER, 6), "001", 1549.95);
		inventory.addGuitar(guitar);
	}
}
