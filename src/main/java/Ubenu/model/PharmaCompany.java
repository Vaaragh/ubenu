package Ubenu.model;

public class PharmaCompany {
	
	private String sysId;
	private String companyName;
	private String country;

	
	public PharmaCompany() {}
	
	public PharmaCompany(String sysId, String companyName, String country) {
		this.sysId = sysId;
		this.companyName = companyName;
		this.country = country;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}



	
	
	

}
