package com.iiht.cognizant.model;

public class UserRegistration {
	private User user;
	private UserRole userRole;
	public UserRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRegistration(User user, UserRole userRole) {
		super();
		this.user = user;
		this.userRole = userRole;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
