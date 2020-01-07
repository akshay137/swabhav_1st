package com.swabhav.expensetracker2.expense.storage;
import com.swabhav.expensetracker2.expense.*;

import android.content.*;
import androidx.room.*;

import java.util.*;

public class StorageService {
	private static StorageService instance;

	private ExpenseDatabase db;

	private StorageService(Context context) {
		db = Room.databaseBuilder(context, ExpenseDatabase.class, "expenses-db").build();
	}

	public static StorageService getInstance(Context context) {
		if (instance == null)
			instance = new StorageService(context);
		return instance;
	}

	public List<Expense> getAll() {
		return this.db.expenseDAO().getAll();
	}

	public void insertAll(List<Expense> expenses) {
		this.db.expenseDAO().insertAll(expenses.toArray(new Expense[expenses.size()]));
	}

	public void delete(Expense expense) {
		this.db.expenseDAO().delete(expense);
	}
}
