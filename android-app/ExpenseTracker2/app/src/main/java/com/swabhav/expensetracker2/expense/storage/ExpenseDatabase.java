package com.swabhav.expensetracker2.expense.storage;

import com.swabhav.expensetracker2.expense.Expense;

import androidx.room.*;

@Database(entities = {Expense.class}, version = 1)
public abstract class ExpenseDatabase extends RoomDatabase {
	public abstract ExpenseDAO expenseDAO();
}
