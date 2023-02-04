package Ubenu.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {
	
	private List<Drug> drugList;
	
	
	public Wishlist() {
		this.drugList = new ArrayList<Drug>();
	}

	public Wishlist(List<Drug> drugList) {
		this.drugList = drugList;
	}

	public List<Drug> getDrugList() {
		return drugList;
	}

	public void setDrugList(List<Drug> drugList) {
		this.drugList = drugList;
	}


	
	

}
