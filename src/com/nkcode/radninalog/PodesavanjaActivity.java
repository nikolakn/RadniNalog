package com.nkcode.radninalog;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import static com.nkcode.radninalog.NKApplication.PREFS_NAME;

public class PodesavanjaActivity extends Activity {
	private Button save;
	private EditText serviser;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.podesavanja);
		// setUpViews();

		setUpViews();

	}

	private void setUpViews() {
		// TODO Auto-generated method stub
		save = (Button) findViewById(R.id.buttonSacuvajsifruServisera);
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			    SharedPreferences.Editor editor = settings.edit();
			    if(serviser.getText().toString()==""){
			    	editor.putString("s1","BB" );
			    } else{
			    	editor.putString("s1",serviser.getText().toString() );	
			    }
			    editor.commit();
			    finish();
			}
		});
		serviser=(EditText) findViewById(R.id.editTextsifraServisera);
	}
}
