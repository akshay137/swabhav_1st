package com.techlabs.dom.test;

import com.techlabs.dom.*;
import java.io.*;

public class DOMTest {
	private static IDOMElement generateLogin()
	{
		DOMGroup root = new DOMGroup("div");
		
		DOMGroup pName = new DOMGroup("p");
		DOMControl nameSpan = new DOMControl("span");
		nameSpan.setInnerHTML("username:");
		DOMControl inputName = new DOMControl("input");
		inputName.setAttributes("type=\"text\"");
		pName.add(nameSpan);
		pName.add(inputName);
		
		DOMGroup pPass = new DOMGroup("p");
		DOMControl passSpan = new DOMControl("span");
		passSpan.setInnerHTML("password:");
		DOMControl inputPass = new DOMControl("input");
		inputPass.setAttributes("type=\"password\"");
		pPass.add(passSpan);
		pPass.add(inputPass);
		
		DOMGroup pSubmit = new DOMGroup("p");
		DOMControl inputSubmit = new DOMControl("input");
		inputSubmit.setAttributes("type=\"submit\" value=\"submit\"");
		pSubmit.add(inputSubmit);
		
		root.add(pName);
		root.add(pPass);
		root.add(pSubmit);
		
		return root;
	}
	
	private static void saveToFile(String filename, String data)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(filename);
			fos.write(data.getBytes());
			fos.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args)
	{
		IDOMElement root = generateLogin();
		String html = root.build();
		System.out.println(html);
		saveToFile("assets/login.html", html);
	}
}
