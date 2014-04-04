package com.nkcode.radninalog;

import com.google.zxing.integration.android.IntentIntegrator;
import com.nkcode.uredjaj.RadniNalozi;
import com.nkcode.uredjaj.Uredjaj;
import static com.nkcode.radninalog.NKApplication.PREFS_NAME;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UredjajActivity extends Activity {
	private NKApplication app;
	//meni button
	private Button radninaloziButton;
	private Button sacuvajButton;
	private Button zakljuciButton;
	private Button obrisiButton;
	
	//edit
	private AutoCompleteTextView model;
	private EditText sbu;
	private EditText sbs;
	private EditText adresa;
	private EditText telefon;
	private EditText ime;
	private EditText korisnik;

	//slike
	private Button telButton;
	private Button mapButton;
	private Button sbuButton;
	private Button sbsButton;
	private AlertDialog obrisisdialog;

	String[] array = { "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten","LG 1","LG 2","LG3" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uredjaj);
		
		
		// setUpViews();
		app = (NKApplication) getApplication();
		setUpViews();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		super.onResume();
		if(app.getTrenutni()==null){
			ucitajpodesavanja();
			Log.i("onresume", "uredjaji: ucitajpodesavanja");
		}

	}


	private void setUpViews() {
		// TODO Auto-generated method stub
		radninaloziButton = (Button) findViewById(R.id.radninalog_button);
		radninaloziButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Dodaj novi nalog
				sacuvaj();
				Intent intent = new Intent(UredjajActivity.this,RadniNaloziActivity.class);
				startActivity(intent);
			}
		});

		sacuvajButton = (Button) findViewById(R.id.sacuvaj_button);
		sacuvajButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sacuvaj();
			}
		});

		zakljuciButton = (Button) findViewById(R.id.zakljuci_button);
		zakljuciButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				zakljuci();
			}
		});

		obrisiButton = (Button) findViewById(R.id.obrisi_button);
		obrisiButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// obrisi uredjaj
				obrisi();
			}
		});
		
		telButton = (Button) findViewById(R.id.telefon_button);
		telButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// pozovi
				String t=telefon.getText().toString().trim();
				String uri = "tel:" + t;
				 Intent intent = new Intent(Intent.ACTION_CALL);
				 intent.setData(Uri.parse(uri));
				 startActivity(intent);
			}
		});
		
		mapButton = (Button) findViewById(R.id.adresa_button);
		mapButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// mapa
				String name=adresa.getText().toString().trim();
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
				Uri.parse("geo:0,0?q="+name+", Srbija"));
				startActivity(intent);
			}
		});
		sbuButton = (Button) findViewById(R.id.sbu_button);
		sbuButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "ALL_CODE_TYPES");
                startActivityForResult(intent, 0);

			}
		});
		sbsButton = (Button) findViewById(R.id.sbs_button);
		sbsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "ALL_CODE_TYPES");
                startActivityForResult(intent, 1);

			}
		});


		model = (AutoCompleteTextView) findViewById(R.id.editTextModel);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, array);
        model.setThreshold(1);
        model.setAdapter(adapter);
        
		sbu = (EditText) findViewById(R.id.editTextSBU);
		sbs = (EditText) findViewById(R.id.editTextSBS);
		adresa = (EditText) findViewById(R.id.editTextAdresa);
		telefon = (EditText) findViewById(R.id.editTextTelefon);
		ime = (EditText) findViewById(R.id.editTextIme);
		korisnik = (EditText) findViewById(R.id.editTextKorisnik);

		setTrenutni();

	}

	private void setTrenutni() {
		if (app.getTrenutni() != null) {
			model.setText(app.getTrenutni().getModel());
			sbu.setText(app.getTrenutni().getSerBrUn());
			sbs.setText(app.getTrenutni().getSerBrSp());
			adresa.setText(app.getTrenutni().getAdresa());
			telefon.setText(app.getTrenutni().getTelefon());
			ime.setText(app.getTrenutni().getIme());
			korisnik.setText(app.getTrenutni().getKorisnik());
		} else{
			model.setText("");
			sbu.setText("");
			sbs.setText("");
			adresa.setText("");
			telefon.setText("");
			ime.setText("");
			korisnik.setText("");
		}
	}

	protected void obrisi() {
		obrisisdialog = new AlertDialog.Builder(this)
				.setTitle(R.string.DialogObrisiNaslov)
				.setMessage(R.string.DijalogObrisiPoruka)
				.setPositiveButton(R.string.Da,new AlertDialog.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
								if (app.getTrenutni() != null) {
									app.removeUredjaj(app.getTrenutni());
									finish();
								} else {
									model.setText("");
									sbu.setText("");
									sbs.setText("");
									adresa.setText("");
									telefon.setText("");
									ime.setText("");
									korisnik.setText("");
								}
							}
						})
				.setNeutralButton(R.string.Ne,new AlertDialog.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {

							}
						})

				.create();
		obrisisdialog.show();

	}

	protected void zakljuci() {
		if (app.getTrenutni() != null) {
			app.getTrenutni().setModel(model.getText().toString());
			app.getTrenutni().setSerBrUn(sbu.getText().toString());
			app.getTrenutni().setSerBrSp(sbs.getText().toString());
			app.getTrenutni().setAdresa(adresa.getText().toString());
			app.getTrenutni().setTelefon(telefon.getText().toString());
			app.getTrenutni().setIme(ime.getText().toString());
			app.getTrenutni().setKorisnik(korisnik.getText().toString());
			app.getTrenutni().setZakljucen(true);
			app.saveUredjaj(app.getTrenutni());
			finish();

		} else {
			Uredjaj u = new Uredjaj();
			u.setModel(model.getText().toString());
			u.setSerBrUn(sbu.getText().toString());
			u.setSerBrSp(sbs.getText().toString());
			u.setAdresa(adresa.getText().toString());
			u.setTelefon(telefon.getText().toString());
			u.setIme(ime.getText().toString());
			u.setKorisnik(korisnik.getText().toString());
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			Date date = new Date();
			u.setDatumPrijema(dateFormat.format(date));
			u.setZakljucen(true);
			app.addUredjaj(u);

			finish();
		}
		// sacuvati u bazi
		// osveziti listu

	}

	protected void sacuvaj() {
		// sacuvaj
		if (app.getTrenutni() != null) {
			app.getTrenutni().setModel(model.getText().toString());
			app.getTrenutni().setSerBrUn(sbu.getText().toString());
			app.getTrenutni().setSerBrSp(sbs.getText().toString());
			app.getTrenutni().setAdresa(adresa.getText().toString());
			app.getTrenutni().setTelefon(telefon.getText().toString());
			app.getTrenutni().setIme(ime.getText().toString());
			app.getTrenutni().setKorisnik(korisnik.getText().toString());
			app.saveUredjaj(app.getTrenutni());
			Log.i("sacuvaj", "uredjaj: "+Long.toString(app.getTrenutni().getId()));
		} else {
			Uredjaj u = new Uredjaj();
			u.setModel(model.getText().toString());
			u.setSerBrUn(sbu.getText().toString());
			u.setSerBrSp(sbs.getText().toString());
			u.setAdresa(adresa.getText().toString());
			u.setTelefon(telefon.getText().toString());
			u.setIme(ime.getText().toString());
			u.setKorisnik(korisnik.getText().toString());
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			Date date = new Date();
			u.setDatumPrijema(dateFormat.format(date));

			app.addUredjaj(u);
			app.setTrenutni(u);
			Log.i("sacuvaj novi uredjaj", "uredjaj: "+Long.toString(app.getTrenutni().getId()));
			
			
		}

	}
	
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                sbu.setText(contents);
            } 
        }
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                sbs.setText(contents);
            } 
        }
    }
    
	public void ucitajpodesavanja() {
		// TODO Auto-generated method stub
	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	    long tu = settings.getLong("t1",Long.MAX_VALUE);
	    if(tu==Long.MAX_VALUE){
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
		    Log.i("!!onpause", "uredjaji: ");
		}
	}
}
