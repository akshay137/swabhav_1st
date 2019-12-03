package com.techlabs.composite;

public class File implements IStorageItem {
	
	private String name;
	private String ext;
	private long size;

	public File(String name, long size)
	{
		this.name = name;
		this.ext = getExtension(name);
		this.setSize(size);
	}
	
	private String getExtension(String name)
	{
		if (name == null)
			return  "";
		if (name.lastIndexOf('.') == name.length() - 1)
			return "";
		return name.substring(name.lastIndexOf('.') + 1);
	}
	
	private void setSize(long size)
	{
		if (size < 0)
			size = 0;
		this.size = size;
	}
	
	@Override
	public void display(String leadingSpace) {
		if (leadingSpace == null)
			leadingSpace = "";
		System.out.printf("%s%s:[%s][%d MB]\n", leadingSpace,
				this.name, this.ext, this.size);
	}

}
