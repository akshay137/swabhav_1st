package com.techlabs.employee.io;

import java.io.*;
import java.net.*;

public class WebReader implements IReadable {

	@Override
	public String readFile(String path) {
		return new String(readFileBytes(path));
	}

	@Override
	public byte[] readFileBytes(String path) {
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
