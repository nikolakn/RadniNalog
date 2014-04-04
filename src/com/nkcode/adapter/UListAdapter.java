package com.nkcode.adapter;

import java.util.ArrayList;

import com.nkcode.radninalog.R;
import com.nkcode.uredjaj.Uredjaj;
import com.nkcode.view.UListItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class UListAdapter extends BaseAdapter {
	private ArrayList<Uredjaj> uredjaj;
	private Context context;

	public UListAdapter(ArrayList<Uredjaj> uredjaj, Context context) {
		super();
		this.uredjaj = uredjaj;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return uredjaj.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return (null == uredjaj) ? null : uredjaj.get(arg0);
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
		UListItem tli;
		if (null == convertView) {
			
			tli = (UListItem) View.inflate(context, R.layout.u_list_item, null);

		} else {
			tli = (UListItem) convertView;
		}
		tli.setUredjaj(uredjaj.get(position));
		return tli;
	}



}
