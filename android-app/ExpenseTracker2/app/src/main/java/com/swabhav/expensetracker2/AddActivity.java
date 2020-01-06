package com.swabhav.expensetracker2;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.support.v7.widget.Toolbar;

import com.swabhav.expensetracker2.expense.Category;
import com.swabhav.expensetracker2.expense.Expense;
import com.swabhav.expensetracker2.expense.ExpenseService;

import java.util.*;

public class AddActivity extends AppCompatActivity {

	private EditText dateEt;
	private EditText amountEt;
	private EditText descriptionEt;
	private Spinner cats;

	private Intent homeIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		dateEt = findViewById(R.id.dateEt);
		amountEt = findViewById(R.id.amountEt);
		descriptionEt = findViewById(R.id.descriptionEt);
		cats = findViewById(R.id.categorySpinner);
		cats.setAdapter(new ArrayAdapter<Category>(this, android.R.layout.select_dialog_item,
				Category.values()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_add, menu);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		System.out.println(item.getItemId());
		switch (item.getItemId()) {
			case R.id.addNote:
				Toast.makeText(this, "Adding new expense", Toast.LENGTH_SHORT).show();
				addExpense();
				break;

				default:
					return super.onOptionsItemSelected(item);
		}
		return true;
	}

	private void addExpense() {
		String description = this.descriptionEt.getText().toString();
		String amountStr = this.amountEt.getText().toString();
		if (amountStr.length() == 0) {
			Toast.makeText(this, "Amount not valid", Toast.LENGTH_SHORT).show();
			return;
		}
		double amount = 0.0;
		try {
			amount = Double.parseDouble(amountStr);
		}
		catch (Exception ignored) {
			Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
			return;
		}
		Expense e = new Expense(amount, description, new Date(), (Category)cats.getSelectedItem());
		ExpenseService.getInstance().addExpense(e);
		onBackPressed();
		return;
	}

	public void showDatePicker(View v) {
		DialogFragment datePicker = DatePicker.getNewInstance(null, (EditText) v);
		datePicker.show(getSupportFragmentManager(), "DatePicker_Add");
	}
}
