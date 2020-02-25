package com.techlabs.jdbc;

import java.sql.*;
import java.util.*;

public class FilterStatementTest {

	public static void main(String[] args) {
		try {
			String strDept;
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter department number");
			strDept = sc.nextLine();
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/swabhav", "root", "root");
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(
					"select * from DEPT where DEPTNO=" + strDept);
//			while(rs.next()) {
//				System.out.printf("DeptId: %d DeptName: %s\n",
//						rs.getInt("DEPTNO"),
//						rs.getString("DNAME"));
//			}
			System.out.println("Department by normal statement");
			printResultSet(rs);
			
			System.out.println("End of main");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
