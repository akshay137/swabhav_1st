package com.swabhav.expensetracker2;

import android.content.*;
import android.view.*;
import android.widget.*;

import com.swabhav.expensetracker2.expense.Expense;

public class ExpenseView extends LinearLayout {

	private Expense expense;
	private TextView priceView;
	private TextView descriptionView;
	private TextView categoryView;

	public ExpenseView(Context context, Expense expense) {
		super(context);
		this.expense = expense;
		init(context);
	}

	private void init(Context ctx) {
		LayoutInflater li = LayoutInflater.from(ctx);
		View v = li.inflate(R.layout.list_expense, this);
		priceView = v.findViewById(R.id.priceText);
		descriptionView = v.findViewById(R.id.descriptionText);
		categoryView = v.findViewById(R.id.categoryText);
		setData();
	}

	private void setData() {
//		price = findViewById(R.id.priceText);
		priceView.setText(Double.toString(this.expense.getPrice()));
		descriptionView.setText(this.expense.getDescription());
		categoryView.setText(this.expense.getCategory().toString());
	}
}
