package com.springboot.demo.models;

/**
 * {@link AuthenticationResponse} represents the structure of the response
 * of an authentication request
 * 
 * @author ikkesh.ramanna
 */
public class AuthenticationResponse {
	
	/**
	 * <p>
	 * The JSON Web token generated
	 * </p>
	 */
	private final String jwt;

	/**
	 * <p>
	 * The Constructor with member variable initialization
	 * </p>
	 */
	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	/* Setter */
	public String getJwt() {
		return jwt;
	}

	
}
