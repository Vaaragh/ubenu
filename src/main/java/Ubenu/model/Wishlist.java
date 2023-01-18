package Ubenu.model;

import Ubenu.model.utilities.IdGen;

public class Wishlist {
	
	private String sysId;

	public Wishlist() {
		this.sysId = IdGen.newID();
	}
	
	public Wishlist(String sysId) {
		this.sysId = sysId;
	}
	
	

}
