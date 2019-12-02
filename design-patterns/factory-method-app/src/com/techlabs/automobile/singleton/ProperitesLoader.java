package com.techlabs.automobile.singleton;

import java.io.*;
import java.util.*;

public class ProperitesLoader implements IConfigLoader {

	@Override
	public Map<String, String> loadConfig(String path) {
		Map<String, String> config = new HashMap<String, String>();
		try
		{
			FileInputStream fis = new FileInputStream(path);
			Properties properties = new Properties();
			properties.load(fis);
			Enumeration<?> keys = properties.keys();
			while (keys.hasMoreElements())
			{
				String key = (String)keys.nextElement();
				String value = properties.getProperty(key);
				config.put(key, value);
			}
		}
		catch (FileNotFoundException exception)
		{
			System.out.println(exception.getMessage());
		}
		catch (IOException exception)
		{
			System.out.println(exception.getMessage());
		}
		return config;
	}

}
