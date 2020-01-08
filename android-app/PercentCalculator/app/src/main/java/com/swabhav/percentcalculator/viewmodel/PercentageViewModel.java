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

	public void onCalculatorSelected(AdapterView<?> parent, View v, int pos, long id) {
		double x = this.calculator.getX();
		double y = this.calculator.getY();
		this.calculator = (PercentageCalculator) parent.getSelectedItem();
		this.calculator.setX(x);
		this.calculator.setY(y);
		this.notifyPropertyChanged(BR.x);
		this.notifyPropertyChanged(BR.y);
		System.out.println(this.calculator);
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
		return Double.toString(this.calculator.getX());
	}

	public void setValueOfX(EditText s) {
		try {
			double x = Double.parseDouble(s.getText().toString());
			this.calculator.setX(x);
		} catch (Exception ignored) {}
	}

	@Bindable
	public String getY() {
		return Double.toString(this.calculator.getY());
	}

	public void setValueOfY(EditText s) {
		try {
			double y = Double.parseDouble(s.getText().toString());
			this.calculator.setY(y);
		} catch (Exception ignored) {}
	}

	public void onCalculate() {
		try {
			double value = this.calculator.calculate();
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

	@Bindable
	public String getAnswer() {
		return this.answer;
	}

}
