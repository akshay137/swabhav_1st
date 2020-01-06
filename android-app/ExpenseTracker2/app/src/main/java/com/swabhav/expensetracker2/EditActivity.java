package com.swabhav.expensetracker2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.support.v7.widget.Toolbar;

import com.swabhav.expensetracker2.expense.*;

import java.text.SimpleDateFormat;

public class EditActivity extends AppCompatActivity {

	private Expense expense;
	private EditText dateEt;
	private EditText amountEt;
	private EditText descriptionEt;
	private Spinner cats;
	private AlertDialog alert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		this.dateEt = findViewById(R.id.dateEt);
		this.amountEt = findViewById(R.id.amountEt);
		this.descriptionEt = findViewById(R.id.descriptionEt);
		this.cats = findViewById(R.id.categorySpinner);
		this.cats.setAdapter(new ArrayAdapter<Category>(this, android.R.layout.select_dialog_item,
				Category.values()));
		init();
		setAlertDialouge();
	}

	private void init() {
		Intent intent = getIntent();
		long expenseId = intent.getLongExtra("id", -1);
		if (expenseId == -1) {
			Toast.makeText(this, "Wrong id for editing", Toast.LENGTH_SHORT).show();
			onBackPressed();
			return;
		}
		this.expense = ExpenseService.getInstance().getExpenseById(expenseId);
		if (this.expense == null) {
			Toast.makeText(this, "Expense not found", Toast.LENGTH_SHORT).show();
			onBackPressed();
			return;
		}

		setData();
	}

	private void setAlertDialouge() {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
		alertBuilder.setMessage(R.string.delete_dialogue)
				.setTitle(R.string.delete_title);
		alert = alertBuilder.create();
		alert.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ExpenseService.getInstance().deleteExpense(EditActivity.this.expense);
				Toast.makeText(EditActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
				onBackPressed();
			}
		});
		alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				alert.dismiss();
			}
		});
	}

	private void setData() {
		this.dateEt.setText(new SimpleDateFormat("MMM dd, yyyy").format(this.expense.getDate()));
		this.amountEt.setText(Double.toString(this.expense.getPrice()));
		this.descriptionEt.setText(this.expense.getDescription());
		this.cats.setSelection(this.expense.getCategory().ordinal());
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.edit:
				editExpense();
				break;

			case R.id.delete:
				deleteExpense();
				break;

				default:
					return super.onOptionsItemSelected(item);
		}
		return true;
	}

	private void editExpense() {
		this.expense.setCategory((Category)this.cats.getSelectedItem());
		this.expense.setDescription(this.descriptionEt.getText().toString());
		String amountStr = this.amountEt.getText().toString();
		if (amountStr.length() == 0) {
			Toast.makeText(this, "Invalid amount", Toast.LENGTH_SHORT).show();
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
		this.expense.setPrice(amount);
		ExpenseService.getInstance().updateExpense(this.expense);

		Toast.makeText(this, "Edit successfull", Toast.LENGTH_SHORT).show();
		onBackPressed();
	}

	private void deleteExpense() {
		alert.show();
	}

	public void showDatePicker(View v) {
		DialogFragment datePicker = DatePicker.getNewInstance(this.expense.getDate(), (EditText)v);
		datePicker.show(getSupportFragmentManager(), "DatePicker_Edit");
	}

}
