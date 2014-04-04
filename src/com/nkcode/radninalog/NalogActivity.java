package com.nkcode.radninalog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static com.nkcode.radninalog.NKApplication.PREFS_NAME;
import com.nkcode.uredjaj.Delovi;
import com.nkcode.uredjaj.Materijal;
import com.nkcode.uredjaj.RadniNalozi;
import com.nkcode.uredjaj.Uredjaj;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NalogActivity extends Activity {
	private NKApplication app;

	private Button saveButton;
	private Button izadjiButton;
	private Button dateButton;
	private TextView id;
	private EditText datum;
	private Spinner spin;

	private EditText opis;
	private EditText opisrada;

	private EditText dolazakSat;
	private EditText dolazakMin;
	private EditText odlazakSat;
	private EditText odlazakMin;

	private AutoCompleteTextView [] mat1;
	private EditText[] matJm1;
	private EditText[] matKol1;

	private EditText[] del;
	private EditText[] delJm;
	private EditText[] delKol;

	private int year;
	private int month;
	private int day;
	
	String[] array = { "Bakarna cev 8'", "Bakarna cev 9'", "Bakarna cev 12'"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nalog);
		// setUpViews();
		app = (NKApplication) getApplication();
		setUpViews();
		setTrenutni();

		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date();
		year = date.getYear();
		month = date.getMonth();
		day = date.getDay();
	}

	private void setUpViews() {
		saveButton = (Button) findViewById(R.id.sacuvaj_button);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sacuvaj();
			}
		});
		izadjiButton = (Button) findViewById(R.id.izlaz_button);
		izadjiButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		dateButton = (Button) findViewById(R.id.myDatePickerButton);
		dateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar c = Calendar.getInstance();
				year = c.get(Calendar.YEAR);
				month = c.get(Calendar.MONTH);
				day = c.get(Calendar.DAY_OF_MONTH);
				showDialog(1);
			}
		});

		id = (TextView) findViewById(R.id.editTextBrNalog);
		datum = (EditText) findViewById(R.id.showMyDate);
		opis = (EditText) findViewById(R.id.editTextopis);
		opisrada = (EditText) findViewById(R.id.editTextrad);
		spin = (Spinner) findViewById(R.id.spinner1);

		dolazakSat = (EditText) findViewById(R.id.editTextDolazakSati);
		dolazakMin = (EditText) findViewById(R.id.editTextDolazakMinuti);
		odlazakSat = (EditText) findViewById(R.id.editTextodlazak);
		odlazakMin = (EditText) findViewById(R.id.editTextOdlazakMinuti);

		mat1 = new AutoCompleteTextView[11];
		matJm1 = new EditText[11];
		matKol1 = new EditText[11];

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, array);
		
		mat1[0] = (AutoCompleteTextView) findViewById(R.id.editTextmat1);     
		matJm1[0] = (EditText) findViewById(R.id.editTextjm1);
		matKol1[0] = (EditText) findViewById(R.id.editTextkol1);

		mat1[1] = (AutoCompleteTextView) findViewById(R.id.editTextmat2);
		matJm1[1] = (EditText) findViewById(R.id.editTextjm2);
		matKol1[1] = (EditText) findViewById(R.id.editTextkol2);

		mat1[2] = (AutoCompleteTextView) findViewById(R.id.editTextmat3);
		matJm1[2] = (EditText) findViewById(R.id.editTextjm3);
		matKol1[2] = (EditText) findViewById(R.id.editTextkol3);

		mat1[3] = (AutoCompleteTextView) findViewById(R.id.editTextmat4);
		matJm1[3] = (EditText) findViewById(R.id.editTextjm4);
		matKol1[3] = (EditText) findViewById(R.id.editTextkol4);

		mat1[4] = (AutoCompleteTextView) findViewById(R.id.editTextmat5);
		matJm1[4] = (EditText) findViewById(R.id.editTextjm5);
		matKol1[4] = (EditText) findViewById(R.id.editTextkol5);

		mat1[5] = (AutoCompleteTextView) findViewById(R.id.editTextmat6);
		matJm1[5] = (EditText) findViewById(R.id.editTextjm6);
		matKol1[5] = (EditText) findViewById(R.id.editTextkol6);

		mat1[6] = (AutoCompleteTextView) findViewById(R.id.editTextmat7);
		matJm1[6] = (EditText) findViewById(R.id.editTextjm7);
		matKol1[6] = (EditText) findViewById(R.id.editTextkol7);

		mat1[7] = (AutoCompleteTextView) findViewById(R.id.editTextmat8);
		matJm1[7] = (EditText) findViewById(R.id.editTextjm8);
		matKol1[7] = (EditText) findViewById(R.id.editTextkol8);

		mat1[8] = (AutoCompleteTextView) findViewById(R.id.editTextmat9);
		matJm1[8] = (EditText) findViewById(R.id.editTextjm9);
		matKol1[8] = (EditText) findViewById(R.id.editTextkol9);

		mat1[9] = (AutoCompleteTextView) findViewById(R.id.editTextmat10);
		matJm1[9] = (EditText) findViewById(R.id.editTextjm10);
		matKol1[9] = (EditText) findViewById(R.id.editTextkol10);

		mat1[10] = (AutoCompleteTextView) findViewById(R.id.editTextmat11);
		matJm1[10] = (EditText) findViewById(R.id.editTextjm11);
		matKol1[10] = (EditText) findViewById(R.id.editTextkol11);

		for(int tr=0; tr<11;tr++){
	        mat1[tr] .setThreshold(1);
	        mat1[tr] .setAdapter(adapter);
		}
		del = new EditText[3];
		delJm = new EditText[3];
		delKol = new EditText[3];

		del[0] = (EditText) findViewById(R.id.editTextdel1);
		delJm[0] = (EditText) findViewById(R.id.editTextjmdel1);
		delKol[0] = (EditText) findViewById(R.id.editTextkoldel1);

		del[1] = (EditText) findViewById(R.id.editTextdel2);
		delJm[1] = (EditText) findViewById(R.id.editTextjmdel2);
		delKol[1] = (EditText) findViewById(R.id.editTextkoldel2);

		del[2] = (EditText) findViewById(R.id.editTextdel3);
		delJm[2] = (EditText) findViewById(R.id.editTextjmdel3);
		delKol[2] = (EditText) findViewById(R.id.editTextkoldel3);

	}

	private void setTrenutni() {
		// TODO Auto-generated method stub
		if (app.getTrenutniRn() != null) {
			id.setText(app.getTrenutniRn().getBrNaloga());
			datum.setText(app.getTrenutniRn().getDatum());
			String[] groups = getResources().getStringArray(R.array.Spiner1);
			if (app.getTrenutniRn().getOpis().equals(groups[0])) {
				spin.setSelection(0);
			}
			if (app.getTrenutniRn().getOpis().equals(groups[1])) {
				spin.setSelection(1);
			}
			if (app.getTrenutniRn().getOpis().equals(groups[2])) {
				spin.setSelection(2);
			}
			opis.setText(app.getTrenutniRn().getNapomena());
			opisrada.setText(app.getTrenutniRn().getOpisRadova());

			dolazakSat.setText(app.getTrenutniRn().getDolazakS());
			dolazakMin.setText(app.getTrenutniRn().getDolazakM());
			odlazakSat.setText(app.getTrenutniRn().getOdlazakS());
			odlazakMin.setText(app.getTrenutniRn().getOdlazakM());

			if (app.getTrenutniRn().getMaterijal(0) != null) {
				mat1[0].setText(app.getTrenutniRn().getMaterijal(0)
						.getUMaterijal().toString());
				matJm1[0].setText(app.getTrenutniRn().getMaterijal(0).getJM());
				matKol1[0].setText(app.getTrenutniRn().getMaterijal(0)
						.getKolicina());
			}
			if (app.getTrenutniRn().getMaterijal(1) != null) {
				mat1[1].setText(app.getTrenutniRn().getMaterijal(1)
						.getUMaterijal());
				matJm1[1].setText(app.getTrenutniRn().getMaterijal(1).getJM());
				matKol1[1].setText(app.getTrenutniRn().getMaterijal(1)
						.getKolicina());
			}
			if (app.getTrenutniRn().getMaterijal(2) != null) {
				mat1[2].setText(app.getTrenutniRn().getMaterijal(2)
						.getUMaterijal());
				matJm1[2].setText(app.getTrenutniRn().getMaterijal(2).getJM());
				matKol1[2].setText(app.getTrenutniRn().getMaterijal(2)
						.getKolicina());
			}
			if (app.getTrenutniRn().getMaterijal(3) != null) {
				mat1[3].setText(app.getTrenutniRn().getMaterijal(3)
						.getUMaterijal());
				matJm1[3].setText(app.getTrenutniRn().getMaterijal(3).getJM());
				matKol1[3].setText(app.getTrenutniRn().getMaterijal(3)
						.getKolicina());
			}
			if (app.getTrenutniRn().getMaterijal(4) != null) {
				mat1[4].setText(app.getTrenutniRn().getMaterijal(4)
						.getUMaterijal());
				matJm1[4].setText(app.getTrenutniRn().getMaterijal(4).getJM());
				matKol1[4].setText(app.getTrenutniRn().getMaterijal(4)
						.getKolicina());
			}
			if (app.getTrenutniRn().getMaterijal(5) != null) {
				mat1[5].setText(app.getTrenutniRn().getMaterijal(5)
						.getUMaterijal());
				matJm1[5].setText(app.getTrenutniRn().getMaterijal(5).getJM());
				matKol1[5].setText(app.getTrenutniRn().getMaterijal(5)
						.getKolicina());
			}
			if (app.getTrenutniRn().getMaterijal(6) != null) {
				mat1[6].setText(app.getTrenutniRn().getMaterijal(6)
						.getUMaterijal());
				matJm1[6].setText(app.getTrenutniRn().getMaterijal(6).getJM());
				matKol1[6].setText(app.getTrenutniRn().getMaterijal(6)
						.getKolicina());
			}

			if (app.getTrenutniRn().getMaterijal(7) != null) {
				mat1[7].setText(app.getTrenutniRn().getMaterijal(7)
						.getUMaterijal());
				matJm1[7].setText(app.getTrenutniRn().getMaterijal(7).getJM());
				matKol1[7].setText(app.getTrenutniRn().getMaterijal(7)
						.getKolicina());
			}

			if (app.getTrenutniRn().getMaterijal(8) != null) {
				mat1[8].setText(app.getTrenutniRn().getMaterijal(8)
						.getUMaterijal());
				matJm1[8].setText(app.getTrenutniRn().getMaterijal(8).getJM());
				matKol1[8].setText(app.getTrenutniRn().getMaterijal(8)
						.getKolicina());
			}
			if (app.getTrenutniRn().getMaterijal(9) != null) {
				mat1[9].setText(app.getTrenutniRn().getMaterijal(9)
						.getUMaterijal());
				matJm1[9].setText(app.getTrenutniRn().getMaterijal(9).getJM());
				matKol1[9].setText(app.getTrenutniRn().getMaterijal(9)
						.getKolicina());
			}
			if (app.getTrenutniRn().getMaterijal(10) != null) {
				mat1[10].setText(app.getTrenutniRn().getMaterijal(10)
						.getUMaterijal());
				matJm1[10]
						.setText(app.getTrenutniRn().getMaterijal(10).getJM());
				matKol1[10].setText(app.getTrenutniRn().getMaterijal(10)
						.getKolicina());
			}
			if (app.getTrenutniRn().getDelovi(0) != null) {
				del[0].setText(app.getTrenutniRn().getDelovi(0)
						.getUgradjeniDelovi());
				delJm[0].setText(app.getTrenutniRn().getDelovi(0).getJM());
				delKol[0].setText(app.getTrenutniRn().getDelovi(0)
						.getKolicina());
			}
			if (app.getTrenutniRn().getDelovi(1) != null) {
				del[1].setText(app.getTrenutniRn().getDelovi(1)
						.getUgradjeniDelovi());
				delJm[1].setText(app.getTrenutniRn().getDelovi(1).getJM());
				delKol[1].setText(app.getTrenutniRn().getDelovi(1)
						.getKolicina());
			}
			if (app.getTrenutniRn().getDelovi(2) != null) {
				del[2].setText(app.getTrenutniRn().getDelovi(2)
						.getUgradjeniDelovi());
				delJm[2].setText(app.getTrenutniRn().getDelovi(2).getJM());
				delKol[2].setText(app.getTrenutniRn().getDelovi(2)
						.getKolicina());
			}
		} else {
			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			Date date = new Date();
			datum.setText(dateFormat.format(date));

		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1:
			return new DatePickerDialog(this, myDateSetListener, year, month,
					day);
		}
		return null;
	}

	OnDateSetListener myDateSetListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker datePicker, int i, int j, int k) {

			year = i;
			month = j;
			day = k;
			String currentDate = new StringBuilder().append(day).append(".")
					.append(month + 1).append(".").append(year).toString();
			datum.setText(currentDate);
		}

		private void updateDisplay() {
			// TODO Auto-generated method stub

		}
	};

	protected void sacuvaj() {
		// sacuvaj

		if(app.getTrenutni()==null){
			Log.i("sacuvaj", "nalog: trenutni null ");
			finish();
			return;
		}
		if (app.getTrenutniRn() != null) {
			//osvezi stari
			app.getTrenutniRn().setDatum(datum.getText().toString());
			app.getTrenutniRn().setDolazakS(dolazakSat.getText().toString());
			app.getTrenutniRn().setDolazakM(dolazakMin.getText().toString());
			app.getTrenutniRn().setOdlazakS(odlazakSat.getText().toString());
			app.getTrenutniRn().setOdlazakM(odlazakMin.getText().toString());
			app.getTrenutniRn().setOpis(spin.getSelectedItem().toString());
			app.getTrenutniRn().setNapomena(opis.getText().toString());
			app.getTrenutniRn().setOpisRadova(opisrada.getText().toString());
			for (int i = 0; i < 11; i++) {
				if (app.getTrenutniRn().getMaterijal(i) != null) {
					app.getTrenutniRn().getMaterijal(i)
							.setUMaterijal(mat1[i].getText().toString());
					app.getTrenutniRn().getMaterijal(i)
							.setJM(matJm1[i].getText().toString());
					app.getTrenutniRn().getMaterijal(i)
							.setKolicina(matKol1[i].getText().toString());
				} else {
					Materijal mm = new Materijal();
					mm.setUMaterijal(mat1[i].getText().toString());
					mm.setJM(matJm1[i].getText().toString());
					mm.setKolicina(matKol1[i].getText().toString());
					app.getTrenutniRn().setMaterijal(i, mm);
				}
			}
			for (int j = 0; j < 3; j++) {
				if (app.getTrenutniRn().getDelovi(j) != null) {
					app.getTrenutniRn().getDelovi(j)
							.setUgradjeniDelovi(del[j].getText().toString());
					app.getTrenutniRn().getDelovi(j)
							.setJM(delJm[j].getText().toString());
					app.getTrenutniRn().getDelovi(j)
							.setKolicina(delKol[j].getText().toString());
				} else {
					Delovi dd = new Delovi();
					dd.setUgradjeniDelovi(del[j].getText().toString());
					dd.setJM(delJm[j].getText().toString());
					dd.setKolicina(delKol[j].getText().toString());
					app.getTrenutniRn().setdelovi(j, dd);
				}
			}
			app.saveTrenutniRn();
		} else {
			//napravi novi
			RadniNalozi rn = new RadniNalozi();
			rn.setDatum(datum.getText().toString());
			rn.setDolazakS(dolazakSat.getText().toString());
			rn.setDolazakM(dolazakMin.getText().toString());
			rn.setOdlazakS(odlazakSat.getText().toString());
			rn.setOdlazakM(odlazakMin.getText().toString());
			rn.setOpis(spin.getSelectedItem().toString());
			rn.setNapomena(opis.getText().toString());
			rn.setOpisRadova(opisrada.getText().toString());
			rn.setUid(app.getTrenutni().getId());
			for (int i = 0; i < 11; i++) {
				if (rn.getMaterijal(i) != null) {
					rn.getMaterijal(i).setUMaterijal(
							mat1[i].getText().toString());
					rn.getMaterijal(i).setJM(matJm1[i].getText().toString());
					rn.getMaterijal(i).setKolicina(
							matKol1[i].getText().toString());
				} else {
					Materijal mm = new Materijal();
					mm.setUMaterijal(mat1[i].getText().toString());
					mm.setJM(matJm1[i].getText().toString());
					mm.setKolicina(matKol1[i].getText().toString());
					rn.setMaterijal(i, mm);
				}
			}
			for (int j = 0; j < 3; j++) {
				if (rn.getDelovi(j) != null) {
					rn.getDelovi(j).setUgradjeniDelovi(
							del[j].getText().toString());
					rn.getDelovi(j).setJM(delJm[j].getText().toString());
					rn.getDelovi(j).setKolicina(delKol[j].getText().toString());
				} else {
					Delovi dd = new Delovi();
					dd.setUgradjeniDelovi(del[j].getText().toString());
					dd.setJM(delJm[j].getText().toString());
					dd.setKolicina(delKol[j].getText().toString());
					rn.setdelovi(j, dd);
				}

			}
		    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);    
		    String ser = settings.getString("s1","BB");
		    
			rn.setBrNaloga(ser+rn.getUid());
			app.getTrenutni().addRadniNalog(rn);
			app.setTrenutniRn(rn);
			app.dodajTrenutniUBazu();
			rn.setBrNaloga(ser+rn.getUid()+"-"+rn.getId());
			
			
		}
	}
	public void ucitajpodesavanja() {
		// TODO Auto-generated method stub
	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);    
	    long trn = settings.getLong("trenutnirn",-1);
	    long tu = settings.getLong("t1",-1);
	    if(tu==-1){
	    	app.setTrenutni(null);
	    }
	    if(trn==-1){
	    	app.setTrenutniRn(null);
	    }
	    for(Uredjaj ur : app.getCurrentUredjaji()){
			if(ur.getId()==tu){
				app.setTrenutni(ur);
				for(RadniNalozi rn : ur.getNalozi()){
					if(rn.getId()==trn){
						app.setTrenutniRn(rn);
						break;
					}
				}
				break;
			}
		}		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(app.getTrenutniRn()!=null){
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putLong("t1", app.getTrenutni().getId()).commit();
		    editor.putLong("trenutnirn", app.getTrenutniRn().getId()).commit();
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(app.getTrenutniRn()==null || app.getTrenutni()==null){
			Log.i("!!onresume", "nalog: ");
			ucitajpodesavanja();
		}

	}
}
