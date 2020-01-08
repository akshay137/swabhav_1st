package com.swabhav.percentcalculator;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.swabhav.percentcalculator.databinding.ActivityMainBinding;
import com.swabhav.percentcalculator.model.PercentageCalculator;
import com.swabhav.percentcalculator.viewmodel.PercentageViewModel;

public class MainActivity extends AppCompatActivity {

	private PercentageViewModel percentageViewModel;
	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.percentageViewModel = new PercentageViewModel(this);
		ActivityMainBinding binding = DataBindingUtil.setContentView(
				this, R.layout.activity_main);
		binding.setCalculator(this.percentageViewModel);
		binding.executePendingBindings();
	}
}
