package com.nkcode.view;


import com.nkcode.radninalog.R;

import com.nkcode.uredjaj.Uredjaj;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UListItem extends LinearLayout {

	private TextView text1;
	private TextView text2;
	private TextView text3;
	private Uredjaj uredjaj;
	public Uredjaj getUredjaj() {
		return uredjaj;
	}
	public void setUredjaj(Uredjaj uredjaj) {
		this.uredjaj = uredjaj;
		text1.setText("SB: "+uredjaj.getSerBrUn());
		text2.setText(uredjaj.getAdresa());
		text3.setText(uredjaj.getIme());
		if(uredjaj.isZakljucen()){
			text1.setTextColor(getResources().getColor(R.color.zakljucen));
			text2.setTextColor(getResources().getColor(R.color.zakljucen));
			text3.setTextColor(getResources().getColor(R.color.zakljucen));
		}
		else{
			text1.setTextColor(getResources().getColor(R.color.nezakljucen));
			text2.setTextColor(getResources().getColor(R.color.nezakljucen));
			text3.setTextColor(getResources().getColor(R.color.nezakljucen));
		}
	}
	public UListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		text1=(TextView)findViewById(R.id.uredjaji_sn);
		text2=(TextView)findViewById(R.id.uredjaji_adresa);
		text3=(TextView)findViewById(R.id.uredjaji_vlasnik);
		
	}


}
