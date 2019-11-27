package com.techlabs.employee.io;

import java.io.*;
import java.net.*;

public class WebReader implements IReadable {

	private String path;
	
	public WebReader(String path)
	{
		this.path = path;
	}
	
	@Override
	public String readFile() {
		return new String(readFileBytes());
	}

	@Override
	public byte[] readFileBytes() {
		try
		{
			URL url = new URL(path);
			int length = url.openConnection().getContentLength();
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			byte[] bytes = new byte[length];
			bis.read(bytes);
			return bytes;
		}
		catch (MalformedURLException exception)
		{
			System.out.println(exception);
			return new byte[0];
		}
		catch (IOException exception)
		{
			System.out.println(exception);
			return new byte[0];
		}
	}

}
