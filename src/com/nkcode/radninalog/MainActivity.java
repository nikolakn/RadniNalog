package com.nkcode.radninalog;

import com.nkcode.adapter.UListAdapter;
import com.nkcode.uredjaj.Uredjaj;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import static com.nkcode.radninalog.NKApplication.PREFS_NAME;

public class MainActivity extends ListActivity {

	private Button addButton;
	private Button scenerButton;
	private NKApplication app;
	private UListAdapter adapter;
	private AboutDialog ADialog;
	private EditText pretragaEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpViews();
		app = (NKApplication) getApplication();
		adapter = new UListAdapter(app.getCurrentUredjaji(), this);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Uredjaj t = (Uredjaj) adapter.getItem(position);
		app.setTrenutni(t);
		Intent intent = new Intent(MainActivity.this, UredjajActivity.class);
		startActivity(intent);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adapter.forcereload();
	}

	private void setUpViews() {
		// TODO Auto-generated method stub
		addButton = (Button) findViewById(R.id.add_button);
		scenerButton = (Button) findViewById(R.id.skeniraj_button);
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Dodaj novi uredjaj
				app.setTrenutni(null);
				SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			    SharedPreferences.Editor editor = settings.edit();
			    editor.putLong("t1", -1);
			    
			      // Commit the edits!
			    editor.commit();
				Intent intent = new Intent(MainActivity.this,
						UredjajActivity.class);
				startActivity(intent);
			}
		});
		scenerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "ALL_CODE_TYPES");
				startActivityForResult(intent, 0);
			}
		});
		pretragaEdit = (EditText) findViewById(R.id.editTextPretraga);
		pretragaEdit
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {

					@Override
					public boolean onEditorAction(TextView v, int actionId,KeyEvent event) {
						if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
							//trazi
							app.traziRazno(pretragaEdit.getText().toString());
							if(app.getTrenutni()!=null){
								Intent intent2 = new Intent(MainActivity.this,UredjajActivity.class);
								startActivity(intent2);
							}
						}
						return false;
					}

				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_oprogramu:
			ADialog = new AboutDialog(this);
			ADialog.show();
			return true;
		case R.id.action_settings:
			Intent intent = new Intent(MainActivity.this,PodesavanjaActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_sinhronizuj:
			//sihronizacija
			//......................
			return true;	
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String trazi = intent.getStringExtra("SCAN_RESULT");
				app.nadjiSb(trazi);
				Intent intent2 = new Intent(MainActivity.this,
						UredjajActivity.class);
				startActivity(intent2);
			}
		}

	}

}
