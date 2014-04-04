package com.nkcode.radninalog;

import java.util.ArrayList;

import com.nkcode.uredjaj.Delovi;
import com.nkcode.uredjaj.Materijal;
import com.nkcode.uredjaj.RadniNalozi;
import com.nkcode.uredjaj.Uredjaj;
import com.nkcode.uredjaj.UredjajSQLiteOpenHelper;

import android.app.Application;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import static com.nkcode.uredjaj.UredjajSQLiteOpenHelper.*;

public class NKApplication extends Application {

	private ArrayList<Uredjaj> currentUredjaji;
	private Uredjaj trenutni=null;
	private RadniNalozi trenutniRn=null;
	private SQLiteDatabase database;

	public static final String PREFS_NAME = "nkpref";
	@Override
	
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		UredjajSQLiteOpenHelper helper=new UredjajSQLiteOpenHelper(this);
		database=helper.getWritableDatabase();
		if (null == currentUredjaji) {
			loadTasks();
		}
		
	}
	public ArrayList<Uredjaj> getCurrentUredjaji() {
		return currentUredjaji;
	}

	public void setCurrentUredjaji(ArrayList<Uredjaj> currentUredjaji) {
		this.currentUredjaji = currentUredjaji;
	}




	private void loadTasks() {
		currentUredjaji=new ArrayList<Uredjaj>();
		
		Cursor taskCursor=database.query(
				UREDJAJI_TABLE, 
				new String[] {U_ID,U_ADRESA,U_DATUMPRIJEMA, U_IME,U_KORISNIK,U_MODEL,U_SERBRSP,U_SERBRUN,U_TELEFON,U_ZAKLJUCEN},
				null,null,null,null,String.format("%s", U_ID));
		taskCursor.moveToFirst();
		Uredjaj u;
		if(!taskCursor.isAfterLast()){
			do{
				long id=taskCursor.getLong(0);
				String adresa=taskCursor.getString(1);
				String datumprijema=taskCursor.getString(2);
				String ime=taskCursor.getString(3);
				String korisnik=taskCursor.getString(4);
				String model=taskCursor.getString(5);
				String serbrsp=taskCursor.getString(6);
				String serbrun=taskCursor.getString(7);
				String telefon=taskCursor.getString(8);			
				String boolValue=taskCursor.getString(9);
				boolean zakljucen=Boolean.parseBoolean(boolValue);
				
				u=new Uredjaj();
				u.setId(id);
				u.setAdresa(adresa);
				u.setDatumPrijema(datumprijema);
				u.setIme(ime);
				u.setKorisnik(korisnik);
				u.setModel(model);
				u.setSerBrSp(serbrsp);
				u.setSerBrUn(serbrun);
				u.setTelefon(telefon);
				u.setZakljucen(zakljucen);
				currentUredjaji.add(u);
			} while (taskCursor.moveToNext());
			taskCursor.close();
		}
		
		Cursor nalogCursor=database.query(
				NALOG_TABLE, 
				new String[] {N_ID,N_U_ID,N_BRNALOGA,N_DATUM,N_NAPOMENA,N_OPIS,N_OPISRADOVA,N_SERVISER,N_DOLAZAKM,N_DOLAZAKS,N_ODLAZAKM,N_ODLAZAKS},
				null,null,null,null,String.format("%s", N_ID));
		nalogCursor.moveToFirst();
		RadniNalozi nl;
		if(!nalogCursor.isAfterLast()){
			do{
				long id=nalogCursor.getLong(0);
				long idu=nalogCursor.getLong(1);
				String brnaloga=nalogCursor.getString(2);
				String datum=nalogCursor.getString(3);
				
				String napomena=nalogCursor.getString(4);
				String opis=nalogCursor.getString(5);
				String opisradova=nalogCursor.getString(6);
				String serviser=nalogCursor.getString(7);
				String dolazakm=nalogCursor.getString(8);
				String dolazaks=nalogCursor.getString(9);
				String odlazakm=nalogCursor.getString(10);
				String odlazaks=nalogCursor.getString(11);
				
				nl=new RadniNalozi();
				nl.setId(id);
				nl.setBrNaloga(brnaloga);
				nl.setDatum(datum);
				nl.setNapomena(napomena);
				nl.setOpis(opis);
				nl.setOpisRadova(opisradova);
				nl.setServiser(serviser);
				nl.setDolazakM(dolazakm);
				nl.setDolazakS(dolazaks);
				nl.setOdlazakM(odlazakm);
				nl.setOdlazakS(odlazaks);
				
				for(Uredjaj ur : currentUredjaji){
					if(ur.getId()==idu){
						ur.addRadniNalog(nl);
						break;
					}
				}
			} while (nalogCursor.moveToNext());
			nalogCursor.close();
		}
		
		
		Cursor matCursor=database.query(
				MATERIJAL_TABLE, 
				new String[] {M_ID,M_RN_ID,M_INDEX,M_NAZIV,M_JM,M_KOLICINA},
				null,null,null,null,String.format("%s", M_ID));
		matCursor.moveToFirst();
		Materijal m;
		if(!matCursor.isAfterLast()){
			do{
				long id=matCursor.getLong(0);
				long rid=matCursor.getLong(1);
				long index=matCursor.getLong(2);
				String naziv=matCursor.getString(3);
				String jm=matCursor.getString(4);
				String kolicina=matCursor.getString(5);
				
				m=new Materijal();
				m.setId(id);
				m.setRnid(rid);
				m.setUMaterijal(naziv);
				m.setJM(jm);
				m.setKolicina(kolicina);

				for(Uredjaj ur : currentUredjaji){
					for(RadniNalozi rn : ur.getNalozi()){
						if(rn.getId()==rid){
							rn.setMaterijal((int)index, m);
							break;
						}
					}
				}
			} while (matCursor.moveToNext());
			matCursor.close();
		}	
		
		
		Cursor delCursor=database.query(
				DELOVI_TABLE, 
				new String[] {D_ID,D_RN_ID,D_INDEX,D_NAZIV,D_JM,D_KOLICINA},
				null,null,null,null,String.format("%s", D_ID));
		delCursor.moveToFirst();
		Delovi d;
		if(!delCursor.isAfterLast()){
			do{
				long id=delCursor.getLong(0);
				long did=delCursor.getLong(1);
				long index=delCursor.getLong(2);
				String naziv=delCursor.getString(3);
				String jm=delCursor.getString(4);
				String kolicina=delCursor.getString(5);
				
				d=new Delovi();
				d.setId(id);
				d.setIdrn(did);
				d.setUgradjeniDelovi(naziv);
				d.setJM(jm);
				d.setKolicina(kolicina);

				for(Uredjaj ur : currentUredjaji){
					for(RadniNalozi rn : ur.getNalozi()){
						if(rn.getId()==did){
							rn.setdelovi((int)index, d);
							break;
						}
					}
				}
			} while (delCursor.moveToNext());
			delCursor.close();
		}	
		setTrenutni(null);
		/*
		Uredjaj u = new Uredjaj();
		u.setZakljucen(true);
		u.setDatumPrijema("12.9.2013");
		u.setAdresa("Zeleznicka 22 Futog");
		u.setIme("Nikola Knezevic");
		u.setKorisnik("Fizicko lice");
		u.setModel("Toplotna Pumpa LG");
		u.setSerBrSp("123456789");
		u.setSerBrUn("987654321");
		u.setTelefon("021896930");
		currentUredjaji.add(u);
		
		RadniNalozi rn=new RadniNalozi();
		rn.setOpis("Montaza");
		rn.setDatum("1.10.2013");
		rn.setBrNaloga("1");
		rn.setNapomena("napomena");
		rn.setOpisRadova("opis radova");
		
		Materijal m1=new Materijal();
		m1.setUMaterijal("cevi te i te");
		m1.setJM("m");
		m1.setKolicina("10");
		rn.setMaterijal(0, m1);
		
		Materijal m2=new Materijal();
		m2.setUMaterijal("materijal");
		m2.setJM("kom");
		m2.setKolicina("2");
		rn.setMaterijal(1, m2);
		
		Materijal m3=new Materijal();
		m3.setUMaterijal("nesto");
		m3.setJM("kom");
		m3.setKolicina("3");
		rn.setMaterijal(2, m3);
		
		u.addRadniNalog(rn);
		
		RadniNalozi rn2=new RadniNalozi();
		rn2.setOpis("Servis");
		rn2.setDatum("1.11.2013");
		rn2.setBrNaloga("2");
		rn2.setNapomena("napomena pppppppppp ppppp ppp ppp");
		rn2.setOpisRadova("opis radova fggg ggg ggggg ggg ggg");
		
		Delovi d1=new Delovi();
		d1.setUgradjeniDelovi("nesto");
		d1.setJM("kom");
		d1.setKolicina("1");
		rn2.setdelovi(0, d1);
		u.addRadniNalog(rn2);
		*/

	}
	public void addUredjaj(Uredjaj u){
		assert(null!=u);
		ContentValues values=new ContentValues();
		values.put(U_ADRESA, u.getAdresa());
		values.put(U_DATUMPRIJEMA, u.getDatumPrijema());
		values.put(U_IME, u.getIme());
		values.put(U_KORISNIK, u.getKorisnik());
		values.put(U_MODEL, u.getModel());
		values.put(U_SERBRSP, u.getSerBrSp());
		values.put(U_SERBRUN, u.getSerBrUn());
		values.put(U_TELEFON, u.getTelefon());
		values.put(U_ZAKLJUCEN, Boolean.toString(u.isZakljucen()));
		
		long id=database.insert(UREDJAJI_TABLE,null,values);
		u.setId(id);
		
		currentUredjaji.add(0, u);
		//currentUredjaji.add(u);
		
	}
	public void saveUredjaj(Uredjaj u){
		assert(null!=u);
		ContentValues values=new ContentValues();
		values.put(U_ADRESA, u.getAdresa());
		values.put(U_DATUMPRIJEMA, u.getDatumPrijema());
		values.put(U_IME, u.getIme());
		values.put(U_KORISNIK, u.getKorisnik());
		values.put(U_MODEL, u.getModel());
		values.put(U_SERBRSP, u.getSerBrSp());
		values.put(U_SERBRUN, u.getSerBrUn());
		values.put(U_TELEFON, u.getTelefon());
		values.put(U_ZAKLJUCEN, Boolean.toString(u.isZakljucen()));
		
		long id=u.getId();
		String where=String.format("%s = %d", U_ID,id);
		
		database.update(UREDJAJI_TABLE, values, where, null);
		
	}
	public Uredjaj getTrenutni() {
		return trenutni;
	}
	public void setTrenutni(Uredjaj trenutni) {
		this.trenutni = trenutni;
		if(trenutni!=null){
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putLong("trenutni", getTrenutni().getId());
		    editor.commit();
		} else{
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putLong("trenutni", Long.MAX_VALUE);
		    editor.commit();
		}
	    
	      // Commit the edits!
	   

	}
	public Long removeUredjaj(Uredjaj ru) {
		if (ru != null) {
			long id = ru.getId();
			
			for(RadniNalozi rn : ru.getNalozi()){
				long idrn=rn.getId();
				String where = String.format("%s = %s", M_RN_ID, idrn);
				database.delete(MATERIJAL_TABLE, where, null);
				where = String.format("%s = %s", D_RN_ID, idrn);
				database.delete(DELOVI_TABLE, where, null);
				where = String.format("%s = %s", N_ID, idrn);
				database.delete(NALOG_TABLE, where, null);
			}
			String where = String.format("%s = %s", U_ID, id);
			database.delete(UREDJAJI_TABLE, where, null);
			
			
			currentUredjaji.remove(ru);
			return id;

		} else {
			return Long.MAX_VALUE;
		}

	}
	public void nadjiSb(String trazi) {
		setTrenutni(null);
		for(Uredjaj u : currentUredjaji){
			if(u.getSerBrUn().equals(trazi)){
				setTrenutni(u);
				return;
			}
			if(u.getSerBrSp().equals(trazi)){
				setTrenutni(u);
				return;
			}
		}
		
	}
	public void traziRazno(String trazi) {
		setTrenutni(null);
		trazi=trazi.toLowerCase();
		for(Uredjaj u : currentUredjaji){
			if(u.getSerBrUn().toLowerCase().equals(trazi)){
				setTrenutni(u);
				return;
			}
			if(u.getSerBrSp().toLowerCase().equals(trazi)){
				setTrenutni(u);
				return;
			}
			if(u.getIme().toLowerCase().equals(trazi)){
				setTrenutni(u);
				return;
			}
			if(u.getAdresa().toLowerCase().equals(trazi)){
				setTrenutni(u);
				return;
			}
			if(u.getTelefon().toLowerCase().equals(trazi)){
				setTrenutni(u);
				return;
			}
			if(u.getKorisnik().toLowerCase().equals(trazi)){
				setTrenutni(u);
				return;
			}
		}
	}

	public RadniNalozi getTrenutniRn() {
		return trenutniRn;
	}
	public void setTrenutniRn(RadniNalozi trenutniRn) {
		this.trenutniRn = trenutniRn;
		if(trenutniRn!=null){
			SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putLong("trenutnirn", getTrenutniRn().getId());
		    editor.commit();
		} else{
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putLong("trenutnirn", Long.MAX_VALUE);
		    editor.commit();
		}


	}
	public void saveTrenutniRn() {
		assert(null!=trenutniRn);
		ContentValues rvalues=new ContentValues();
		rvalues.put(N_BRNALOGA, trenutniRn.getBrNaloga());
		
		rvalues.put(N_BRNALOGA, trenutniRn.getBrNaloga());
		rvalues.put(N_DATUM, trenutniRn.getDatum());
		rvalues.put(N_DOLAZAKM, trenutniRn.getDolazakM());
		rvalues.put(N_DOLAZAKS, trenutniRn.getDolazakS());
		rvalues.put(N_NAPOMENA, trenutniRn.getNapomena());
		
		rvalues.put(N_ODLAZAKM, trenutniRn.getOdlazakM());
		rvalues.put(N_ODLAZAKS, trenutniRn.getOdlazakS());
		rvalues.put(N_OPIS, trenutniRn.getOpis());
		rvalues.put(N_OPISRADOVA, trenutniRn.getOpisRadova());
		rvalues.put(N_SERVISER, trenutniRn.getServiser());
		rvalues.put(N_U_ID, trenutniRn.getUid());

		long id1=trenutniRn.getId();
		String where=String.format("%s = %d", N_ID,id1);
		database.update(NALOG_TABLE, rvalues, where, null);
		
		for (int i = 0; i < 11; i++) {
			if (getTrenutniRn().getMaterijal(i) != null) {
				ContentValues mvalues=new ContentValues();
				mvalues.put(M_INDEX, trenutniRn.getMaterijal(i).getIndex());
				mvalues.put(M_NAZIV,trenutniRn.getMaterijal(i).getUMaterijal());
				mvalues.put(M_JM,trenutniRn.getMaterijal(i).getJM());
				mvalues.put(M_KOLICINA,trenutniRn.getMaterijal(i).getKolicina());
				mvalues.put(M_RN_ID,trenutniRn.getId());
				long id=getTrenutniRn().getMaterijal(i).getId();
				String where1=String.format("%s = %d", M_ID,id);
				database.update(MATERIJAL_TABLE, mvalues, where1, null);
			} 
		}
		for (int j = 0; j < 3; j++) {
			if (getTrenutniRn().getDelovi(j) != null) {
				ContentValues dvalues=new ContentValues();
				dvalues.put(D_INDEX, trenutniRn.getDelovi(j).getIndex());
				dvalues.put(D_NAZIV,trenutniRn.getDelovi(j).getUgradjeniDelovi());
				dvalues.put(D_JM,trenutniRn.getDelovi(j).getJM());
				dvalues.put(D_KOLICINA,trenutniRn.getDelovi(j).getKolicina());
				dvalues.put(D_RN_ID,trenutniRn.getId());
				long id=getTrenutniRn().getDelovi(j).getId();
				String where2=String.format("%s = %d", D_ID,id);
				database.update(DELOVI_TABLE, dvalues, where2, null);
			} 
		}

	}
	public void dodajTrenutniUBazu() {
		// TODO Auto-generated method stub
		assert(null!=trenutniRn);
		ContentValues rvalues=new ContentValues();
		rvalues.put(N_BRNALOGA, trenutniRn.getBrNaloga());
		
		rvalues.put(N_BRNALOGA, trenutniRn.getBrNaloga());
		rvalues.put(N_DATUM, trenutniRn.getDatum());
		rvalues.put(N_DOLAZAKM, trenutniRn.getDolazakM());
		rvalues.put(N_DOLAZAKS, trenutniRn.getDolazakS());
		rvalues.put(N_NAPOMENA, trenutniRn.getNapomena());
		
		rvalues.put(N_ODLAZAKM, trenutniRn.getOdlazakM());
		rvalues.put(N_ODLAZAKS, trenutniRn.getOdlazakS());
		rvalues.put(N_OPIS, trenutniRn.getOpis());
		rvalues.put(N_OPISRADOVA, trenutniRn.getOpisRadova());
		rvalues.put(N_SERVISER, trenutniRn.getServiser());
		rvalues.put(N_U_ID, trenutniRn.getUid());
		long id1=database.insert(NALOG_TABLE,null,rvalues);
		trenutniRn.setId(id1);
		
		for (int i = 0; i < 11; i++) {
			if (getTrenutniRn().getMaterijal(i) != null) {
				ContentValues mvalues=new ContentValues();
				mvalues.put(M_INDEX, trenutniRn.getMaterijal(i).getIndex());
				mvalues.put(M_NAZIV,trenutniRn.getMaterijal(i).getUMaterijal());
				mvalues.put(M_JM,trenutniRn.getMaterijal(i).getJM());
				mvalues.put(M_KOLICINA,trenutniRn.getMaterijal(i).getKolicina());
				mvalues.put(M_RN_ID,trenutniRn.getId());
				long id=database.insert(MATERIJAL_TABLE,null,mvalues);
				trenutniRn.getMaterijal(i).setId(id);
				trenutniRn.getMaterijal(i).setRnid(trenutniRn.getId());
			} 
		}
		for (int j = 0; j < 3; j++) {
			if (getTrenutniRn().getDelovi(j) != null) {
				ContentValues dvalues=new ContentValues();
				dvalues.put(D_INDEX, trenutniRn.getDelovi(j).getIndex());
				dvalues.put(D_NAZIV,trenutniRn.getDelovi(j).getUgradjeniDelovi());
				dvalues.put(D_JM,trenutniRn.getDelovi(j).getJM());
				dvalues.put(D_KOLICINA,trenutniRn.getDelovi(j).getKolicina());
				dvalues.put(D_RN_ID,trenutniRn.getId());
				long id=database.insert(DELOVI_TABLE,null,dvalues);
				trenutniRn.getDelovi(j).setId(id);
				trenutniRn.getDelovi(j).setId(trenutniRn.getId());
			} 
		}

	}

}
