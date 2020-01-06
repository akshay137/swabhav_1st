package com.swabhav.expensetracker2.expense;

import java.util.*;

public class ExpenseService {
	private static ExpenseService instance;

	private List<Expense> expenses;

	private ExpenseService() {
		this.expenses = new ArrayList<Expense>();
	}

	public static ExpenseService getInstance() {
		if (instance == null)
			instance = new ExpenseService();
		return instance;
	}

	public List<Expense> getExpenses() {
		return this.expenses;
	}

	public boolean addExpense(Expense expense) {
		return this.expenses.add(expense);
	}

	public Expense getExpenseById(long id) {
		for (Expense e : this.expenses) {
			if (e.getExpensId() == id)
				return e;
		}
		return null;
	}
}
