package com.swabhav.expensetracker2;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;

import java.util.*;

import com.swabhav.expensetracker2.expense.*;
import com.swabhav.expensetracker2.expense.storage.DBResonse;

public class MainActivity extends AppCompatActivity {

	private LinearLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		generateListView();

		final Activity activity = this;
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(activity, AddActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onRestart() {
		super.onRestart();
		generateListView();
	}

	private void generateListView() {
		this.container = findViewById(R.id.container);
		this.container.removeAllViews();
		final DBProgress progress = new DBProgress(this,
				"Loading", "Fetching records");
		progress.show();
		ExpenseService.getInstance(this).getExpenses(new DBResonse() {
			@Override
			public void onComplete(Object response) {
				List<Expense> expenseList = (List<Expense>)response;
				for (Expense expense : expenseList) {
					ExpenseView ev = new ExpenseView(MainActivity.this, expense);
					MainActivity.this.container.addView(ev);
				}
				progress.dismiss();
			}
		});
	}
}
