package com.swabhav.expensetracker2.expense;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatters {

	private static Formatters instance;

	private SimpleDateFormat dateFormat;

	private Formatters() {
		this.dateFormat = new SimpleDateFormat("MMM dd, yyyy");
	}

	public static Formatters getInstance() {
		if (instance == null) {
			instance = new Formatters();
		}
		return instance;
	}

	public String formatDate(Date date) {
		return this.dateFormat.format(date);
	}

	public Date parseDate(String dateStr) {
		try {
			return this.dateFormat.parse(dateStr);
		} catch (Exception ignored) {
			return new Date();
		}
	}
}
