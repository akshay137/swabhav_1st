package com.swabhav.expensetracker2.expense;

import java.text.SimpleDateFormat;
import java.util.*;

import androidx.room.*;

@Entity(tableName = "expenses")
public class Expense {
	@PrimaryKey
	private long expenseId;

	@ColumnInfo(name = "price")
	private double price;

	@ColumnInfo(name = "description")
	private String description;

	@ColumnInfo(name = "date")
	private String dateStr;

	@Ignore
	private Date date;

	@ColumnInfo(name = "category")
	private String catStr;

	@Ignore
	private Category category;

	public Expense(double price, String description, Date date, Category category) {
		this.expenseId = new Date().getTime();
		this.price = price;
		this.date = date;
		this.dateStr = new SimpleDateFormat("MMM dd, yyyy").format(this.date);
		this.description = description;
		this.category = category;
		this.catStr = this.category.toString();
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

	public Expense() {
		this(0.0, "", new Date(), Category.Misc);
	}

	public long getExpenseId() {
		return expenseId;
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
		this.dateStr = Formatters.getInstance().formatDate(this.date);
	}

	public void setCategory(Category category) {
		this.category = category;
		this.catStr = this.category.toString();
	}

	public void setAs(Expense expense) {
		this.category = expense.getCategory();
		this.date = expense.getDate();
		this.description = expense.getDescription();
		this.price = expense.getPrice();
	}

	public void setExpenseId(long expenseId) {
		this.expenseId = expenseId;
	}

	public String getDateStr() {
		return dateStr;
	}

	public String getCatStr() {
		return catStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
		this.date = Formatters.getInstance().parseDate(this.dateStr);
	}

	public void setCatStr(String catStr) {
		this.catStr = catStr;
		this.category = Category.valueOf(catStr);
	}

	@Override
	public int hashCode() {
		return (int)this.expenseId;
	}

	@Override
	public boolean equals(Object other) {
		return this.expenseId == ((Expense)other).getExpenseId();
	}
}
