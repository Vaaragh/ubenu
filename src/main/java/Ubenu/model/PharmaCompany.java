package Ubenu.model;

public class PharmaCompany {
	
	private String sysId;
	private String companyName;
	private String country;
	private boolean active;
	
	public PharmaCompany() {}
	
	public PharmaCompany(String companyName, String country, boolean active) {
		this.companyName = companyName;
		this.country = country;
		this.active = active;
	}

	public PharmaCompany(String sysId, String companyName, String country, boolean active) {
		this.sysId = sysId;
		this.companyName = companyName;
		this.country = country;
		this.active = active;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "PharmaCompany [sysId=" + sysId + ", companyName=" + companyName + ", country=" + country + ", active="
				+ active + "]";
	}
	
	
	

}
