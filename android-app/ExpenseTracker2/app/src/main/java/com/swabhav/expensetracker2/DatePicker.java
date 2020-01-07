package com.swabhav.expensetracker2;

import android.app.*;
import android.os.*;
import android.support.v4.app.DialogFragment;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	private Date date = null;
	private EditText et;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		if (this.date == null) {
			this.date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(this.date);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);

		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		this.date.setTime(c.getTime().getTime());
		this.et.setText(new SimpleDateFormat("MMM dd, yyyy").format(this.date));
	}

	public static DatePicker getNewInstance(Date date, EditText et) {
		DatePicker dp = new DatePicker();
		dp.setDate(date);
		dp.setEditText(et);
		return dp;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return this.date;
	}

	public void setEditText(EditText et) {
		this.et = et;
	}
}
