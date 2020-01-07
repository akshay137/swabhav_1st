package com.swabhav.mvvmtest;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

public class UserViewModel extends BaseObservable {

	@Bindable
	private User user;

	public UserViewModel() {
		this.user = new User();
	}

	@Bindable
	public String getName() {
		return this.user.getName();
	}

	@Bindable
	public void setName(String name) {
		this.user.setName(name);
		notifyPropertyChanged(BR.name);
		notifyPropertyChanged(BR._all);
	}
}
