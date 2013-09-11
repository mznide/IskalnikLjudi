package si.fri.iskalnikOseb.dataHelpers;

public class ZdravnikStructure {
	String ime;
	String nazivOE;
	String nazivIzvajalca;

	public ZdravnikStructure(String ime, String nazivOE, String nazivIzvajalca) {
		super();
		this.ime = ime;
		this.nazivOE = nazivOE;
		this.nazivIzvajalca = nazivIzvajalca;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getNazivOE() {
		return nazivOE;
	}

	public void setNazivOE(String nazivOE) {
		this.nazivOE = nazivOE;
	}

	public String getNazivIzvajalca() {
		return nazivIzvajalca;
	}

	public void setNazivIzvajalca(String nazivIzvajalca) {
		this.nazivIzvajalca = nazivIzvajalca;
	}

}
