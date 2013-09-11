package si.fri.iskalnikOseb.dataHelpers;

public class SicrisStructure {
	String name;
	String phoneNumber;
	String faxNumber;
	String emailAddress;
	String website;

	public SicrisStructure(String name, String phoneNumber, String faxNumber,
			String emailAddress, String website) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.emailAddress = emailAddress;
		this.website = website;
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

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}
