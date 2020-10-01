package com.springboot.demo.models;

/**
 * {@link AuthenticationRequest} represents the structure 
 * of an authentication request
 * 
 * @author ikkesh.ramanna
 */
public class AuthenticationRequest {
	
	/**
	 * <p>
	 * The username od the user
	 * </p>
	 */
	private String username;
	
	/**
	 * <p>
	 * The plain-text password of the user
	 * </p>
	 */
	private String password;
	
	/**
	 * <p>
	 * The Constructor with no member variable initialization
	 * </p>
	 */
	public AuthenticationRequest() {
	}

	/**
	 * <p>
	 * The Constructor with member variable initialization
	 * </p>
	 */
	public AuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/*
	 * Getter and Setters
	 */
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
	
	
	
	
	
	
}
