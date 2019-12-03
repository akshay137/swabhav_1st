package com.techlabs.composite.test;

import com.techlabs.composite.*;

public class CompositeTest {
	private static void populateFolder(Folder folder)
	{
		folder.addStorageItem(new File("harry potter 1.avi", 300));
		folder.addStorageItem(new File("fantastic beasts.mp4", 800));
		folder.addStorageItem(new File("rush hour.mkv", 300));
		
		Folder comedy = new Folder("comedy");
		comedy.addStorageItem(new File("Johny English.mkv", 1400));
		comedy.addStorageItem(new File("Rush Hour 2.mp4", 2000));
		folder.addStorageItem(comedy);
		
		Folder sciFi = new Folder("sci-fi");
		sciFi.addStorageItem(new File("Avengers.mp4", 1750));
		Folder ironMan = new Folder("Iron Man");
		ironMan.addStorageItem(new File("Iron Man.mp4", 1200));
		ironMan.addStorageItem(new File("Iron Man 2.mkv", 800));
		ironMan.addStorageItem(new File("Iron Man 3.mkv", 1500));
		sciFi.addStorageItem(ironMan);
		
		Folder fantasy = new Folder("fantasy");
		fantasy.addStorageItem(new File("Lord of the rings.mp4", 3000));
		fantasy.addStorageItem(new File("How to train your dragon.avi", 600));
		folder.addStorageItem(fantasy);
	}
	
	public static void main(String[] args)
	{
		Folder root = new Folder("movies");
		populateFolder(root);
		root.display("");
	}
}
