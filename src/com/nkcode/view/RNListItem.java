package com.nkcode.view;

import com.nkcode.radninalog.R;
import com.nkcode.uredjaj.RadniNalozi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RNListItem extends LinearLayout {
	private TextView text1;
	private TextView text2;
	private TextView text3;
	private RadniNalozi rn;
	public RadniNalozi getRn() {
		return rn;
	}
	public void setRn(RadniNalozi r) {
		this.rn = r;
		text1.setText(rn.getBrNaloga());
		text2.setText(rn.getDatum());
		text3.setText(rn.getOpis());

	}
	public RNListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		text1=(TextView)findViewById(R.id.rn_id);
		text2=(TextView)findViewById(R.id.rn_datum);
		text3=(TextView)findViewById(R.id.rn_opis);
		
	}
}
