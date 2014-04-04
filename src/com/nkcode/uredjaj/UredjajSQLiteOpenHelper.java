package com.nkcode.uredjaj;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UredjajSQLiteOpenHelper extends SQLiteOpenHelper {


	public static final String DB_NAME="rn3_db.sqlite";
	public static final int VERSION=1;
	
	public static final String UREDJAJI_TABLE = "uredjaji";
	public static final String U_ID = "id";
	public static final String U_SERBRUN = "SerBrUn";
	public static final String U_SERBRSP = "SerBrSp";
	public static final String U_ADRESA = "adresa";
	public static final String U_TELEFON = "telefon";
	public static final String U_IME = "ime";
	public static final String U_KORISNIK = "korisnik";
	public static final String U_MODEL = "model";
	public static final String U_DATUMPRIJEMA = "datum";
	public static final String U_ZAKLJUCEN = "zakljucen";
	
	public static final String MATERIJAL_TABLE = "materijal";
	public static final String M_ID = "id";
	public static final String M_RN_ID = "rnid";
	public static final String M_INDEX = "index2";
	public static final String M_NAZIV = "naziv";
	public static final String M_JM = "jm";
	public static final String M_KOLICINA = "kolicina";
	
	public static final String DELOVI_TABLE = "delovi";
	public static final String D_ID = "id";
	public static final String D_INDEX = "index2";
	public static final String D_RN_ID = "rnid";
	public static final String D_NAZIV = "naziv";
	public static final String D_JM = "jm";
	public static final String D_KOLICINA = "kolicina";
	
	public static final String NALOG_TABLE = "Nalozi";
	public static final String N_ID = "id";
	public static final String N_U_ID = "uid";
	public static final String N_DATUM = "datum";
	public static final String N_OPIS = "opis";
	public static final String N_BRNALOGA = "brnaloga";	
	public static final String N_NAPOMENA = "napomena";
	public static final String N_OPISRADOVA = "opisradova";
	public static final String N_SERVISER = "serviser";
	public static final String N_DOLAZAKS = "dolazaks";
	public static final String N_DOLAZAKM = "dolazakm";
	public static final String N_ODLAZAKS = "odlazaks";
	public static final String N_ODLAZAKM = "odlazakm";
	
	
	public UredjajSQLiteOpenHelper(Context context) {
		super(context, DB_NAME, null,VERSION );
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		createTables(db);
	}
	private void createTables(SQLiteDatabase db) {
		db.execSQL(
				"create table "+UREDJAJI_TABLE+"("+
				U_ID+" integer primary key autoincrement not null,"+
				U_SERBRUN+" text," +
				U_SERBRSP+" text," +
				
				U_ADRESA+" text," +
				U_TELEFON+" text," +
				U_IME+" text," +
				U_KORISNIK+" text," +				
				U_MODEL+" text," +
				U_DATUMPRIJEMA+" text," +
				U_ZAKLJUCEN+" text" +
				");"
				);	
		
		db.execSQL(
				"create table "+MATERIJAL_TABLE+"("+
				M_ID+" integer primary key autoincrement not null,"+
				M_RN_ID+" integer," +
				M_INDEX+" integer," +
				M_NAZIV+" text," +
				M_JM+" text," +				
				M_KOLICINA+" text" +
				");"
				);	
		
		db.execSQL(
				"create table "+DELOVI_TABLE+"("+
				D_ID+" integer primary key autoincrement not null,"+
				D_RN_ID+" integer," +
				D_INDEX+" integer," +
				D_NAZIV+" text," +
				D_JM+" text," +				
				D_KOLICINA+" text" +
				");"
				);	
		
		db.execSQL(
				
				"create table "+NALOG_TABLE+"("+
				N_ID+" integer primary key autoincrement not null,"+
				N_U_ID+" integer," +
				N_DATUM+" text," +
				N_OPIS+" text," +
				N_BRNALOGA+" text," +
				N_NAPOMENA+" text," +
				N_OPISRADOVA+" text," +
				N_SERVISER+" text," +
				N_DOLAZAKS+" text," +
				N_DOLAZAKM+" text," +
				N_ODLAZAKS+" text," +
				N_ODLAZAKM+" text" +
				");"
				);
				
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
