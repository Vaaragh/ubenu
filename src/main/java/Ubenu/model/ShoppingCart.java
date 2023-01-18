package Ubenu.model;

import Ubenu.model.utilities.IdGen;

public class ShoppingCart {
	
	private String sysId;

	public ShoppingCart() {
		this.sysId = IdGen.newID();
	}
	
	public ShoppingCart(String sysId) {
		this.sysId = sysId;
	}
	
	
	

}
