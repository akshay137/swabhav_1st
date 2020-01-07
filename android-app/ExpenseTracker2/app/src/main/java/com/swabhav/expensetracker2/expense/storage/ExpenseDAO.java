package com.swabhav.expensetracker2.expense.storage;

import com.swabhav.expensetracker2.expense.*;

import java.util.*;

import androidx.room.*;

@Dao
public interface ExpenseDAO {
	@Query("SELECT * FROM expenses")
	public List<Expense> getAll();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	public void insertAll(Expense... expenses);

	@Update
	public void updateAll(Expense... expenses);

	@Delete
	public void delete(Expense expense);
}
