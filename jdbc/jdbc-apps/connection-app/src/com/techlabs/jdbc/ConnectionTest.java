package com.techlabs.jdbc;

import java.sql.*;
import java.util.*;

public class ConnectionTest {
	public static void main(String[] args) {
		try {			
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(
					"jdbc:mysql://localhost/swabhav"
					+ "?user=root"
					+ "&password=root");
			System.out.printf("Concrete class: %s\n", c.getClass());
			System.out.printf("Database: %s\n", c.getCatalog());
			c.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
