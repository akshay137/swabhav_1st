package com.swabhav.mvvmtest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.swabhav.mvvmtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

	private UserViewModel userViewModel = new UserViewModel();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(
				this, R.layout.activity_main);
		activityMainBinding.setUser(userViewModel);
		activityMainBinding.executePendingBindings();

	}
}
