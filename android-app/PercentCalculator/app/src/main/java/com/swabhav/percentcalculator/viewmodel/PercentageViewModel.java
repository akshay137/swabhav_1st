package com.swabhav.percentcalculator.viewmodel;

import android.content.Context;
import android.databinding.*;
import android.view.*;
import android.widget.*;

import com.android.databinding.library.baseAdapters.*;
import com.swabhav.percentcalculator.model.*;

public class PercentageViewModel extends BaseObservable {

	private PercentageCalculator calculator;
	private String answer;
	private String x;
	private String y;
	private Context context;

	private ObservableList<PercentageCalculator> calculatorList;

	public PercentageViewModel(Context context) {
		this.context = context;
		initCalculatorList();
	}

	private void initCalculatorList() {
		this.calculatorList = new ObservableArrayList<PercentageCalculator>();
		this.calculatorList.add(new SimplePercentage());
		this.calculatorList.add(new ReversePercentage());
		this.calculatorList.add(new PercentDifference());
		this.calculator = this.calculatorList.get(0);
	}

	@Bindable
	public ObservableList<PercentageCalculator> getCalculatorList() {
		return this.calculatorList;
	}

	@BindingAdapter("android:entries")
	public void setCalculators(View view, Object unused) {
		Spinner spinner = (Spinner)view;
		spinner.setAdapter(new ArrayAdapter<PercentageCalculator>(this.context,
				android.R.layout.simple_spinner_dropdown_item, this.calculatorList));
	}

	@Bindable
	public String getX() {
		return this.x;
	}

	@Bindable
	public void setX(String x) { this.x = x; }

	@Bindable
	public String getY() { return this.y; }

	@Bindable
	public void setY(String y) { this.y = y; }

	@Bindable
	public String getAnswer() { return this.answer; }

	public void onCalculatorSelected(AdapterView<?> parent, View v, int pos, long id) {
		this.calculator = (PercentageCalculator) parent.getSelectedItem();
		System.out.println(this.calculator);
	}

	public void onCalculate() {
		double xValue = 0;
		double yValue = 0;
		try {
			xValue = Double.parseDouble(this.x);
		} catch (Exception ignored) {
			Toast.makeText(this.context, "Could not parse X", Toast.LENGTH_SHORT).show();
			return;
		}

		try {
			yValue = Double.parseDouble(this.y);
		} catch (Exception ignored) {
			Toast.makeText(this.context, "Could not parse Y", Toast.LENGTH_LONG).show();
			return;
		}

		try {
			double value = this.calculator.calculate(xValue, yValue);
			if (Double.isNaN(value)) {
				this.answer = "You eneterd something which I could not make sense of";
			} else {
				this.answer = Double.toString(value);
			}
		} catch (Exception igonred) {
			this.answer = "Invalid values were given";
		}
		notifyPropertyChanged(BR.answer);
	}
}
