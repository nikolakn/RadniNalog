package com.nkcode.uredjaj;

public class Materijal {
	private long id;
	private long index;
	private String UMaterijal;
	private String JM;
	private String Kolicina;
	private long rnid;
	public String getUMaterijal() {
		return UMaterijal;
	}
	public void setUMaterijal(String uMaterijal) {
		UMaterijal = uMaterijal;
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
	public long getRnid() {
		return rnid;
	}
	public void setRnid(long rnid) {
		this.rnid = rnid;
	}
}
