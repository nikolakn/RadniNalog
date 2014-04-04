package com.nkcode.uredjaj;

public class RadniNalozi {
	private long id;
	private long uid;
	private String Datum;
	private String Opis;
	private String BrNaloga;
	
	private String Napomena;
	private String OpisRadova;
	private String Serviser;
	
	private String DolazakS;
	private String DolazakM;
	private String OdlazakS;
	private String OdlazakM;
	public String getDatum() {
		return Datum;
	}
	public void setDatum(String datum) {
		Datum = datum;
	}
	public String getOpis() {
		return Opis;
	}
	public void setOpis(String opis) {
		Opis = opis;
	}
	public String getBrNaloga() {
		return BrNaloga;
	}
	public void setBrNaloga(String brNaloga) {
		BrNaloga = brNaloga;
	}
	public String getNapomena() {
		return Napomena;
	}
	public void setNapomena(String napomena) {
		Napomena = napomena;
	}
	public String getOpisRadova() {
		return OpisRadova;
	}
	public void setOpisRadova(String opisRadova) {
		OpisRadova = opisRadova;
	}
	public String getServiser() {
		return Serviser;
	}
	public void setServiser(String serviser) {
		Serviser = serviser;
	}

	private Materijal[] materijal;
	private Delovi[] delovi;
	
	public RadniNalozi(){
		materijal=new Materijal[11];
		delovi=new Delovi[3];
	}	
	
	public void setMaterijal(int index,Materijal m){
		m.setIndex(index);
		materijal[index]=m;
	}
	public void setdelovi(int index,Delovi d){
		d.setIndex(index);
		delovi[index]=d;
	}
	public Materijal getMaterijal(int i){
		return materijal[i];
	}
	public Delovi getDelovi(int i){
		return delovi[i];
	}
	public String getDolazakS() {
		return DolazakS;
	}
	public void setDolazakS(String dolazakS) {
		DolazakS = dolazakS;
	}
	public String getDolazakM() {
		return DolazakM;
	}
	public void setDolazakM(String dolazakM) {
		DolazakM = dolazakM;
	}
	public String getOdlazakS() {
		return OdlazakS;
	}
	public void setOdlazakS(String odlazakS) {
		OdlazakS = odlazakS;
	}
	public String getOdlazakM() {
		return OdlazakM;
	}
	public void setOdlazakM(String odlazakM) {
		OdlazakM = odlazakM;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	
}
