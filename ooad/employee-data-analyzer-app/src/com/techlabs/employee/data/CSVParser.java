package com.techlabs.employee.data;

public class CSVParser implements IParsable {
	
	IParseAddable container;
	
	public CSVParser(IParseAddable container)
	{
		this.container = container;
	}
	
	@Override
	public boolean parse(String csv) {
		String[] lines = csv.split(System.getProperty("line.separator"));
		for (String line : lines)
		{
			String[] values = line.split(",");
			container.add((Object[])values);
		}
		return false;
	}

}
