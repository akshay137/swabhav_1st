package com.techlabs.account.serializer;

import com.techlabs.account.*;
import java.io.*;

public class AccountSerializer {
	public static boolean serializeAccount(Account acc, String name)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(name);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(acc);
			oos.close();
			return true;
		}
		catch (Exception exception)
		{
			System.out.println(exception);
			return false;
		}
	}
	
	public static Account deserializeAccount(String filename)
	{
		try
		{
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Account acc = (Account)ois.readObject();
			ois.close();
			return acc;
		}
		catch (Exception exception)
		{
			System.out.println(exception);
			exception.printStackTrace();
			return null;
		}
	}
}
