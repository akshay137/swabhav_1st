package com.techlabs.composite;

import java.util.*;

public class Folder implements IStorageItem {
	private String name;
	private List<IStorageItem> children;
	
	public Folder(String name)
	{
		this.name = name;
		children = new ArrayList<IStorageItem>();
	}
	
	public boolean addStorageItem(IStorageItem item)
	{
		return children.add(item);
	}

	@Override
	public void display(String leadingSpace) {
		if (leadingSpace == null)
			leadingSpace = "";
		System.out.printf("%s%s\n", leadingSpace, this.name);
		leadingSpace += "--";
		for (IStorageItem item : children)
			item.display(leadingSpace);
	}
}
