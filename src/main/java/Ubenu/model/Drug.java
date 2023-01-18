package Ubenu.model;

import Ubenu.model.enums.EDrugFormulation;

public class Drug {
	
	private String sysId;
	private String drugName;
	private String drugCode;
	private String drugDescription;
	private String contraindications;
	private EDrugFormulation drugFormulation;
	private float rating;
	private String imagePath;
	private int inventory;
	private float price;
	private PharmaCompany pharmaCompany;
	private DrugCategory drugCategory;
	private boolean active;

	
	public Drug() {
	}

	public Drug(String sysId, String drugName, String drugCode, String drugDescription, String contraindications,
			EDrugFormulation drugFormulation, float rating, String imagePath, int inventory, float price,
			PharmaCompany pharmaCompany, DrugCategory drugCategory, boolean active) {
		this.sysId = sysId;
		this.drugName = drugName;
		this.drugCode = drugCode;
		this.drugDescription = drugDescription;
		this.contraindications = contraindications;
		this.drugFormulation = drugFormulation;
		this.rating = rating;
		this.imagePath = imagePath;
		this.inventory = inventory;
		this.price = price;
		this.pharmaCompany = pharmaCompany;
		this.drugCategory = drugCategory;
		this.active = active;
	}
	
	
	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getDrugDescription() {
		return drugDescription;
	}

	public void setDrugDescription(String drugDescription) {
		this.drugDescription = drugDescription;
	}

	public String getContraindications() {
		return contraindications;
	}

	public void setContraindications(String contraindications) {
		this.contraindications = contraindications;
	}

	public EDrugFormulation getDrugFormulation() {
		return drugFormulation;
	}

	public void setDrugFormulation(EDrugFormulation drugFormulation) {
		this.drugFormulation = drugFormulation;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public PharmaCompany getPharmaCompany() {
		return pharmaCompany;
	}

	public void setPharmaCompany(PharmaCompany pharmaCompany) {
		this.pharmaCompany = pharmaCompany;
	}

	public DrugCategory getDrugCategory() {
		return drugCategory;
	}

	public void setDrugCategory(DrugCategory drugCategory) {
		this.drugCategory = drugCategory;
	}
	
	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Drug [sysId=" + sysId + ", drugName=" + drugName + "]";
	}
	
	
	
	
	
	
	
}
