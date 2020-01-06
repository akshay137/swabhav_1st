package com.swabhav.expensetracker2.expense;

import java.util.*;

public class Expense {
	private long expensId;
	private double price;
	private String description;
	private Date date;
	private Category category;

	private static long idGenerator = 0;

	public Expense(double price, String description, Date date, Category category) {
		this.expensId = idGenerator++;
		this.price = price;
		this.date = date;
		this.description = description;
		this.category = category;
	}

	public Expense(double price, String description) {
		this(price, description, new Date(), Category.Misc);
	}

	public Expense(double price, Date date) {
		this(price, "", date, Category.Misc);
	}

	public Expense(double price) {
		this(price, "", new Date(), Category.Misc);
	}

	public long getExpensId() {
		return expensId;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}

	public Category getCategory() {
		return category;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
