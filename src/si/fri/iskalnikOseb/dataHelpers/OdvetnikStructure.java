package si.fri.iskalnikOseb.dataHelpers;

public class OdvetnikStructure {
	String ime;
	String naslov;
	String kraj;
	String telefon;

	public OdvetnikStructure(String ime, String naslov, String kraj,
			String telefon) {
		super();
		this.ime = ime;
		this.naslov = naslov;
		this.kraj = kraj;
		this.telefon = telefon;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

}
