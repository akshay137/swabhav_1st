package com.swabhav.expensetracker2.expense;

import android.content.*;

import com.swabhav.expensetracker2.expense.storage.*;

import java.util.*;

public class ExpenseService {
	private static ExpenseService instance;

	private List<Expense> expenses;

	private StorageService storageService;

	private ExpenseService(Context context) {
//		this.expenses = new ArrayList<Expense>();
		this.storageService = StorageService.getInstance(context);
		this.expenses = this.storageService.getAll();
		if (this.expenses == null) {
			this.expenses = new ArrayList<Expense>();
		}
	}

	public static ExpenseService getInstance(Context context) {
		if (instance == null)
			instance = new ExpenseService(context);
		return instance;
	}

	public List<Expense> getExpenses() {
		return this.expenses;
	}

	public boolean addExpense(Expense expense) {
		boolean ret = this.expenses.add(expense);
		this.saveExpenses();
		return ret;
	}

	public Expense getExpenseById(long id) {
		for (Expense e : this.expenses) {
			if (e.getExpenseId() == id)
				return e;
		}
		return null;
	}

	public void deleteExpense(Expense expense) {
		this.expenses.remove(expense);
		this.storageService.delete(expense);
		this.saveExpenses();
	}

	public boolean updateExpense(Expense expense) {
		Expense old = getExpenseById(expense.getExpenseId());
		if (old == null) {
			return false;
		}
		old.setAs(expense);
		this.saveExpenses();

		return true;
	}

	public void saveExpenses() {
		this.storageService.insertAll(this.expenses);
	}
}
