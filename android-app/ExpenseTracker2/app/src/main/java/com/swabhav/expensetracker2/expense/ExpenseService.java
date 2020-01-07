package com.swabhav.expensetracker2.expense;

import android.content.*;
import android.os.*;

import com.swabhav.expensetracker2.expense.storage.*;

import java.util.*;

public class ExpenseService {
	private static ExpenseService instance;

	private List<Expense> expenses;

	private StorageService storageService;

	private ExpenseService(Context context) {
		this.storageService = StorageService.getInstance(context);
		this.expenses = new ArrayList<>();
	}

	public static ExpenseService getInstance(Context context) {
		if (instance == null)
			instance = new ExpenseService(context);
		return instance;
	}

	public void getExpenses(DBResonse onComplete) {
		new DatabaseRead().execute(onComplete);
	}

	public void addExpense(Expense expense, DBResonse response) {
		this.expenses.add(expense);
		this.saveExpenses(response);
	}

	public Expense getExpenseById(long id) {
		for (Expense e : this.expenses) {
			if (e.getExpenseId() == id)
				return e;
		}
		return null;
	}

	public void deleteExpense(Expense expense, DBResonse response) {
		this.expenses.remove(expense);
		new DatabaseDelete(expense).execute(response);
	}

	public void updateExpense(Expense expense, DBResonse response) {
		Expense old = getExpenseById(expense.getExpenseId());
		if (old == null) {
			return;
		}
		old.setAs(expense);
		this.saveExpenses(response);
	}

	public void saveExpenses(DBResonse response) {
		new DatabaseSave().execute(response);
//		this.storageService.insertAll(this.expenses);
	}

//	Async tasks
	private class DatabaseRead extends AsyncTask<DBResonse, Void, List<Expense>> {
		DBResonse[] responses;
		@Override
		protected List<Expense> doInBackground(DBResonse... responses) {
			this.responses = responses;
			ExpenseService.this.expenses = ExpenseService.this.storageService.getAll();
			return ExpenseService.this.expenses;
		}

		@Override
		protected void onPostExecute(List<Expense> expenses) {
			for (DBResonse response : this.responses)
				response.onComplete(expenses);
			return;
		}

		@Override
		protected void onProgressUpdate(Void... unused) {
			return;
		}
	}

	private class DatabaseSave extends AsyncTask<DBResonse, Void, Void> {
		DBResonse[] responses;

		@Override
		protected Void doInBackground(DBResonse... responses) {
			this.responses = responses;
			ExpenseService.this.storageService.insertAll(ExpenseService.this.expenses);
			return null;
		}

		@Override
		protected void onPostExecute(Void unused) {
			for (DBResonse response : this.responses)
				response.onComplete(unused);
		}
	}

	private class DatabaseDelete extends AsyncTask<DBResonse, Void, Void> {
		private Expense expense;
		private DBResonse[] responses;

		public DatabaseDelete(Expense expense) {
			this.expense = expense;
		}

		@Override
		protected Void doInBackground(DBResonse... responses) {
			this.responses = responses;
			ExpenseService.this.storageService.delete(this.expense);
			ExpenseService.this.storageService.insertAll(ExpenseService.this.expenses);
			return null;
		}

		@Override
		public void onPostExecute(Void unused) {
			for (DBResonse response : this.responses)
				response.onComplete(unused);
		}
	}
}
