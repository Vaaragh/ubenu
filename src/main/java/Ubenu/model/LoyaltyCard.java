package Ubenu.model;

public class LoyaltyCard {
	
	private User user;
	private int numberOfPoints;
	private float discountPerPoint;
	
	
	public LoyaltyCard() {
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNumberOfPoints() {
		return numberOfPoints;
	}
	
	public void setNumberOfPoints(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}


	public float getDiscountPerPoint() {
		return discountPerPoint;
	}


	public void setDiscountPerPoint(float discountPerPoint) {
		this.discountPerPoint = discountPerPoint;
	}

}
