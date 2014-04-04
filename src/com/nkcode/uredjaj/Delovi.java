package com.nkcode.uredjaj;

public class Delovi {
	
	public String getUgradjeniDelovi() {
		return UgradjeniDelovi;
	}
	public void setUgradjeniDelovi(String ugradjeniDelovi) {
		UgradjeniDelovi = ugradjeniDelovi;
	}
	public String getJM() {
		return JM;
	}
	public void setJM(String jM) {
		JM = jM;
	}
	public String getKolicina() {
		return Kolicina;
	}
	public void setKolicina(String kolicina) {
		Kolicina = kolicina;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIndex() {
		return index;
	}
	public void setIndex(long index) {
		this.index = index;
	}
	public long getIdrn() {
		return idrn;
	}
	public void setIdrn(long idrn) {
		this.idrn = idrn;
	}
	private String UgradjeniDelovi;
	private String JM;
	private String Kolicina;
	private long id;
	private long index;
	private long idrn;
}
