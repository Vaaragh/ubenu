package Ubenu.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Ubenu.model.enums.ERole;



public class User {
	
	private String sysId;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String address;
	private String phoneNumber;
	private LocalDateTime registartionDateTime;
	private ERole role;
	private boolean active;
	
	public User() {
	}
	
	
	public User(String sysId, String username, String password, String email, String firstName, String lastName,
			LocalDate dateOfBirth, String address, String phoneNumber, LocalDateTime registartionDateTime, ERole role,
			boolean active) {
		this.sysId = sysId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.registartionDateTime = registartionDateTime;
		this.role = role;
		this.active = active;
	}


	public String getSysId() {
		return sysId;
	}


	public void setSysId(String sysId) {
		this.sysId = sysId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public LocalDateTime getRegistartionDateTime() {
		return registartionDateTime;
	}


	public void setRegistartionDateTime(LocalDateTime registartionDateTime) {
		this.registartionDateTime = registartionDateTime;
	}


	public ERole getRole() {
		return role;
	}


	public void setRole(ERole role) {
		this.role = role;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", registartionDateTime="
				+ registartionDateTime + "]";
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
}
