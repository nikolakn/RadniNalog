package com.nkcode.radninalog;

import com.nkcode.adapter.RnListAdapter;
import com.nkcode.uredjaj.RadniNalozi;
import com.nkcode.uredjaj.Uredjaj;
import static com.nkcode.radninalog.NKApplication.PREFS_NAME;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class RadniNaloziActivity extends ListActivity {
	private NKApplication app;
	private Button nalogButton;
	private Button cButton;
	private RnListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radninalozi);
       //setUpViews();
        app = (NKApplication)getApplication();
		if(app.getTrenutni()==null){
			ucitajpodesavanja();
		}
        if(app.getTrenutni()==null){
			Log.i("oncreate", "radninalozi: trenutni null");
			finish();
			return;
        }
		adapter = new RnListAdapter(app.getTrenutni().getNalozi(), this);
		setListAdapter(adapter);
        setUpViews();
    }
	private void setUpViews() { 
		// TODO Auto-generated method stub
		nalogButton=(Button)findViewById(R.id.novinalog_button);
		nalogButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				// Dodaj novi uredjaj
				app.setTrenutniRn(null);
				Intent intent=new Intent(RadniNaloziActivity.this,NalogActivity.class);
				startActivity(intent);
			}
		});	
		cButton=(Button)findViewById(R.id.izlaz_button);
		cButton.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				finish();
			}
		});	
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ucitajpodesavanja();
		Log.i("onresume", "radninalozi: ucitaj podesvanja");
		if(app.getTrenutni()!=null){
			adapter.forcereload();
		}
		else{
			Log.i("onresume", "radninalozi: trenutni null");
			finish();
			
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		RadniNalozi t = (RadniNalozi) adapter.getItem(position);
		app.setTrenutniRn(t);
		Intent intent = new Intent(RadniNaloziActivity.this, NalogActivity.class);
		startActivity(intent);

	}
	
	public void ucitajpodesavanja() {
		// TODO Auto-generated method stub
	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	    long tu = settings.getLong("t1",-1);
	    Log.i("!!tu-radninalozi ucitaj", "rn: "+Long.toString(tu));
	    if(tu==-1){
	    	app.setTrenutni(null);
	    	return;
	    }

	    for(Uredjaj ur : app.getCurrentUredjaji()){
			if(ur.getId()==tu){
				app.setTrenutni(ur);
				break;
			}
		}	
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(app.getTrenutni()!=null){
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putLong("t1", app.getTrenutni().getId());
		      // Commit the edits!
		    editor.commit();
		    Log.i("!!onpause", "radninalozi: ");
		}
	}
}
