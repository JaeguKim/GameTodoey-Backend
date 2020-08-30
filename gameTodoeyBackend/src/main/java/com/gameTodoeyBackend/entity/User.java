package com.gameTodoeyBackend.entity;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	public User(String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	
	
}
