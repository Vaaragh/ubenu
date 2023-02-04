package Ubenu.model;

import java.time.LocalDate;

public class Comment {
	

	private String textContent;
	private int rating;
	private LocalDate dateOf;
	private User user;
	private Drug drug;
	private boolean anon;
	
	
	public Comment() {
	}


	public Comment(String textContent, int rating, LocalDate dateOf, User user, Drug drug, boolean anon) {
		this.textContent = textContent;
		this.rating = rating;
		this.dateOf = dateOf;
		this.user = user;
		this.drug = drug;
		this.anon = anon;
	}


	public String getTextContent() {
		return textContent;
	}


	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public LocalDate getDateOf() {
		return dateOf;
	}


	public void setDateOf(LocalDate dateOf) {
		this.dateOf = dateOf;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Drug getDrug() {
		return drug;
	}


	public void setDrug(Drug drug) {
		this.drug = drug;
	}


	public boolean isAnon() {
		return anon;
	}


	public void setAnon(boolean anon) {
		this.anon = anon;
	}
	
	
	
	
	

}
