package Ubenu.model;

import Ubenu.model.utilities.IdGen;

public class LoyaltyCard {
	
	private String sysId;
	private int numberOfPoints;
	private float discountPerPoint;
	private boolean active;
	
	
	public LoyaltyCard() {
		this.sysId = IdGen.newID();
	}
	
	public LoyaltyCard(String sysId) {
		this.sysId = sysId;
	}
	
	

}
