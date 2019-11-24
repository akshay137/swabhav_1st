package com.techlabs.ch5.test;

import java.util.*;
import com.techlabs.ch5.*;

public class DotComGame {
	private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
	private int numOfGuesses = 0;
	private GameHelper helper = new GameHelper();
	
	private void setUpGame()
	{
		System.out.println("Your goal is to sink three dot coms");
		String[] names = {"Pets.com", "eToys.com", "Go2.com"};
		for (String name : names)
		{
			DotCom dotCom = new DotCom();
			dotCom.setName(name);
			dotCom.setLocations(helper.placeDotCom(3));
			dotComList.add(dotCom);
			System.out.print(name + " ");
		}
		System.out.println("\nTry to sink them all");
	}
	
	private void startPlaying()
	{
		while (!dotComList.isEmpty())
		{
			String guess = helper.getUserInput("Enter a guess");
			checkUserGuess(guess);
		}
		finishGame();
	}
	
	private void checkUserGuess(String guess)
	{
		numOfGuesses++;
		String result = "miss";
		for (DotCom dotCom : dotComList)
		{
			result = dotCom.checkYourself(guess);
			if (result.equals("hit"))
				break;
			if (result.equals("kill"))
			{
				dotComList.remove(dotCom);
				break;
			}
		}
		System.out.println(result);
	}
	
	private void finishGame()
	{
		System.out.println("All dot coms are dead! Your stock is now worthless");
		if (numOfGuesses <= 18)
		{
			System.out.println("It only took you " + numOfGuesses + " guesses.");
			System.out.println("Yo got out before your options sank");
		}
		else
		{
			System.out.println("Took you long enough.");
			System.out.println(numOfGuesses + " guesses;");
			System.out.println("Fish are dancing with your stock");
		}
	}
	
	public static void main(String[] args)
	{
		DotComGame game = new DotComGame();
		game.setUpGame();
		game.startPlaying();
	}
}
