package Ubenu.model;

import java.util.List;

public class Customer {

	private User user;
	private Wishlist wishlist;
	private ShoppingCart shoppingCart;
	private LoyaltyCard loyaltyCard;
	
	
	
	
	
	public Customer() {
	}
	
	public Customer(User user) {
		this.user =user;
		this.wishlist = new Wishlist();
		this.shoppingCart = new ShoppingCart();
		this.loyaltyCard = new LoyaltyCard();
	}
	
	public Customer(User user, Wishlist wishlist, ShoppingCart shoppingCart, LoyaltyCard loyaltyCard) {
		this.user = user;
		this.wishlist = wishlist;
		this.shoppingCart = shoppingCart;
		this.loyaltyCard = loyaltyCard;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Wishlist getWishlist() {
		return wishlist;
	}
	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public LoyaltyCard getLoyaltyCard() {
		return loyaltyCard;
	}
	public void setLoyaltyCard(LoyaltyCard loyaltyCard) {
		this.loyaltyCard = loyaltyCard;
	}
	
	
	
	
}
