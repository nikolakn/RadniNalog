package com.nkcode.uredjaj;

import java.util.ArrayList;

public class Uredjaj {
	private long id;
	private String SerBrUn;
	private String SerBrSp;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSerBrUn() {
		return SerBrUn;
	}

	public void setSerBrUn(String serBrUn) {
		SerBrUn = serBrUn;
	}

	public String getSerBrSp() {
		return SerBrSp;
	}

	public void setSerBrSp(String serBrSp) {
		SerBrSp = serBrSp;
	}

	public String getAdresa() {
		return Adresa;
	}

	public void setAdresa(String adresa) {
		Adresa = adresa;
	}

	public String getTelefon() {
		return Telefon;
	}

	public void setTelefon(String telefon) {
		Telefon = telefon;
	}

	public String getIme() {
		return Ime;
	}

	public void setIme(String ime) {
		Ime = ime;
	}

	public String getKorisnik() {
		return Korisnik;
	}

	public void setKorisnik(String korisnik) {
		Korisnik = korisnik;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public String getDatumPrijema() {
		return DatumPrijema;
	}

	public void setDatumPrijema(String datumPrijema) {
		DatumPrijema = datumPrijema;
	}

	public boolean isZakljucen() {
		return zakljucen;
	}

	public void setZakljucen(boolean zakljucen) {
		this.zakljucen = zakljucen;
	}

	public ArrayList<RadniNalozi> getNalozi() {
		return nalozi;
	}

	public void setNalozi(ArrayList<RadniNalozi> nalozi) {
		this.nalozi = nalozi;
	}

	private String Adresa;
	private String Telefon;
	private String Ime;
	private String Korisnik;
	private String Model;
	private String DatumPrijema;
	
	private boolean zakljucen;
	
	private ArrayList<RadniNalozi> nalozi;
	
	public void addRadniNalog(RadniNalozi rn){
		assert(null!=rn);
		nalozi.add(rn);		
	}
	
	public Uredjaj(){
		nalozi=new ArrayList<RadniNalozi>();
	}
}
