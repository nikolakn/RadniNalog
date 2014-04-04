package com.nkcode.adapter;

import java.util.ArrayList;

import com.nkcode.radninalog.R;
import com.nkcode.uredjaj.RadniNalozi;
import com.nkcode.view.RNListItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RnListAdapter extends BaseAdapter {

	private ArrayList<RadniNalozi> rnalog;
	private Context context;

	public RnListAdapter(ArrayList<RadniNalozi> rn, Context context) {
		super();
		this.rnalog = rn;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return rnalog.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return (null == rnalog) ? null : rnalog.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public void forcereload() {
		notifyDataSetChanged();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RNListItem tli;
		if (null == convertView) {
			
			tli = (RNListItem) View.inflate(context, R.layout.rn_list_item, null);

		} else {
			tli = (RNListItem) convertView;
		}
		tli.setRn(rnalog.get(position));
		return tli;
	}


}
