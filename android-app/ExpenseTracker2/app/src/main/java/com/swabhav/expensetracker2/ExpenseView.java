package com.swabhav.expensetracker2;

import android.content.*;
import android.view.*;
import android.widget.*;

import com.swabhav.expensetracker2.expense.Expense;

import java.text.SimpleDateFormat;

public class ExpenseView extends LinearLayout {

	private Expense expense;
	private TextView priceView;
	private TextView descriptionView;
	private TextView categoryView;
	private TextView dateView;
	private Intent editIntent;

	public ExpenseView(Context context, Expense expense) {
		super(context);
		this.expense = expense;
		init(context);
		editIntent = new Intent(context, EditActivity.class);
	}

	private void init(Context ctx) {
		LayoutInflater li = LayoutInflater.from(ctx);
		View v = li.inflate(R.layout.list_expense, this);
		final Context context = ctx;
		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editIntent.putExtra("id", ExpenseView.this.expense.getExpenseId());
				context.startActivity(editIntent);
			}
		});
		priceView = v.findViewById(R.id.priceText);
		descriptionView = v.findViewById(R.id.descriptionText);
		categoryView = v.findViewById(R.id.categoryText);
		dateView = v.findViewById(R.id.dateText);
		setData();
	}

	private void setData() {
//		price = findViewById(R.id.priceText);
		priceView.setText(Double.toString(this.expense.getPrice()));
		descriptionView.setText(this.expense.getDescription());
		categoryView.setText(this.expense.getCategory().toString());
		dateView.setText(new SimpleDateFormat("MMM dd, yyyy").format(this.expense.getDate()));
	}
}
