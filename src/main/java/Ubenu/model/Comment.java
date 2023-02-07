package Ubenu.model;

import java.time.LocalDate;

public class Comment {
	
	private String sysId;
	private String textContent;
	private int rating;
	private LocalDate dateOf;
	private User user;
	private boolean anon;
	
	
	public Comment() {
	}
	
	
	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
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



	public boolean isAnon() {
		return anon;
	}


	public void setAnon(boolean anon) {
		this.anon = anon;
	}
	
	
	
	
	

}
