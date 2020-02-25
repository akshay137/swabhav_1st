package com.techlabs.jdbc;

import java.sql.*;

public class StatementTest {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(
					"jdbc:mysql://localhost/swabhav", "root", "root");
			System.out.printf("Connected to: %s\n", c.getCatalog());
			
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select * from DEPT");
			while (rs.next()) {
				System.out.printf("DeptID: %d Name: %s\n",
						rs.getInt("DEPTNO"),
						rs.getString("DNAME"));
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			for (StackTraceElement st : e.getStackTrace())
				System.err.println(st);
		}
	}

}
