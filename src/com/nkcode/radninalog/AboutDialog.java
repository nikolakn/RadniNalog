package com.nkcode.radninalog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class AboutDialog extends Dialog implements android.view.View.OnClickListener {

	public AboutDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.dialog);
	        setTitle("O Programu");
	    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		dismiss();
	}

}
