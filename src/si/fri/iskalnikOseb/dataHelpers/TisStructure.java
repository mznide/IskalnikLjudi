package si.fri.iskalnikOseb.dataHelpers;

public class TisStructure {	
	
	private String name;
	private String phoneNumber;
	private String address;
	private String zip;

	
	public TisStructure(String name, String phoneNumber, String address,
			String zip) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.zip = zip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	
}
