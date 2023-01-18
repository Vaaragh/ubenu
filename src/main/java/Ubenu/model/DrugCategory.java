package Ubenu.model;

public class DrugCategory {
	
	private String sysId;
	private String categoryName; //sysId equivalent?
	private String useCase;
	private String description;
	
	
	public DrugCategory() {
	}

	public DrugCategory(String sysId, String categoryName, String useCase, String description) {
		this.sysId = sysId;
		this.categoryName = categoryName;
		this.useCase = useCase;
		this.description = description;
	}

	

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUseCase() {
		return useCase;
	}

	public void setUseCase(String useCase) {
		this.useCase = useCase;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "DrugCategory [categoryName=" + categoryName + "]";
	}
	
	
	
	
	
	
	
	
	
}
