package com.techlabs.transaction.test;

import java.sql.*;
import com.techlabs.transaction.*;

public class TransactionTest {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection(
					"jdbc:mysql://localhost/swabhav", "root", "root");
			Customer.getInstance().createTable(c);
			Customer.getInstance().addCustomer(c, 101, "abc", 10000.0);
			ResultSet rs = Customer.getInstance().getCustomers(c, 101);
			printResultSet(rs);
			Merchant.getInstance().createTable(c);
			Merchant.getInstance().addMerchant(c, 101, "dmart", 1000.0);
			rs = Merchant.getInstance().getMerchant(c, 101);
			printResultSet(rs);
			
//			transaction
			try {
				c.setAutoCommit(false);
				c.beginRequest();
				int res = Customer.getInstance().purchase(c, 101, 2000);
				if (res == 0)
					throw new Exception("no update");
				res = Merchant.getInstance().purchase(c, 102, 2000);
				if (res == 0)
					throw new Exception("no upadte");
				c.endRequest();
				c.commit();
			} catch (Exception e) {
				System.out.println("Rolling back");
//				e.printStackTrace();
				c.rollback();
			} finally {
				c.setAutoCommit(true);
			}
			System.out.println("After transaction");
			rs = Customer.getInstance().getCustomers(c, 101);
			printResultSet(rs);
			rs = Merchant.getInstance().getMerchant(c, 101);
			printResultSet(rs);
			System.out.println("End of main");
		}
		catch(Exception e) {
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
