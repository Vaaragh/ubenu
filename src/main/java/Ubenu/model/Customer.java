package Ubenu.model;

import java.util.List;

public class Customer {

	private User user;
	private List<Drug> wishlist;
	private List<ShoppingItem> shoppingCart;
	private List<CustomerOrder> shoppingHistory;
	private LoyaltyCard loyaltyCard;
	
	
	
	
	public Customer() {
	}




	public Customer(User user, List<Drug> wishlist, List<ShoppingItem> shoppingCart,
			List<CustomerOrder> shoppingHistory, LoyaltyCard loyaltyCard) {
		this.user = user;
		this.wishlist = wishlist;
		this.shoppingCart = shoppingCart;
		this.shoppingHistory = shoppingHistory;
		this.loyaltyCard = loyaltyCard;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public List<Drug> getWishlist() {
		return wishlist;
	}




	public void setWishlist(List<Drug> wishlist) {
		this.wishlist = wishlist;
	}




	public List<ShoppingItem> getShoppingCart() {
		return shoppingCart;
	}




	public void setShoppingCart(List<ShoppingItem> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}




	public List<CustomerOrder> getShoppingHistory() {
		return shoppingHistory;
	}




	public void setShoppingHistory(List<CustomerOrder> shoppingHistory) {
		this.shoppingHistory = shoppingHistory;
	}




	public LoyaltyCard getLoyaltyCard() {
		return loyaltyCard;
	}




	public void setLoyaltyCard(LoyaltyCard loyaltyCard) {
		this.loyaltyCard = loyaltyCard;
	}
	
	
	
	

	
	
	
}