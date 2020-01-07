package com.swabhav.expensetracker2;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

public class DBProgress {
	private ProgressDialog progressDialog;

	public DBProgress(Context context, String title, String message) {
		this.progressDialog = new ProgressDialog(context);
		this.progressDialog.setTitle(title);
		this.progressDialog.setMessage(message);
		this.progressDialog.setIndeterminate(true);
		this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		this.progressDialog.setCancelable(false);
	}

	public void show() {
		this.progressDialog.show();
	}

	public void dismiss() {
		this.progressDialog.dismiss();
	}

}
