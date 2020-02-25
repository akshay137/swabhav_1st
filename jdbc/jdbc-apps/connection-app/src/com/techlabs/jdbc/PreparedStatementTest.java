package com.techlabs.jdbc;

import java.sql.*;
import java.util.*;

public class PreparedStatementTest {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(
					"jdbc:mysql://localhost/swabhav", "root", "root");
			System.out.println("Enter dept no");
			Scanner sc = new Scanner(System.in);
			String strDept = sc.nextLine();
			PreparedStatement pst = c.prepareStatement(
					"select * from DEPT where DEPTNO=?");
			pst.setString(1, strDept);
			if (pst.execute()) {
				ResultSet rs = pst.getResultSet();
				printResultSet(rs);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End of main");
	}
	
	private static void printResultSet(ResultSet rs) throws Exception {
		ResultSetMetaData rsm = rs.getMetaData();
		int cc = rsm.getColumnCount();
		while (rs.next()) {
			System.out.printf("%d :: ", rs.getRow());
			for (int i = 1; i <= cc; i++) {
				System.out.printf("%s ", rs.getString(i));
			}
			System.out.println();
		}
	}

}
