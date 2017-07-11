package com.team1.data;

public class User {

	private int id;
	private String mail, user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public User(int id, String mail, String user) {
		super();
		this.id = id;
		this.mail = mail;
		this.user = user;
	}
	
	public User() {
		super();
	}	
}