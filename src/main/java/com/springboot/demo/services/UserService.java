package com.springboot.demo.services;

import com.springboot.demo.exceptions.ApiRequestException;
import com.springboot.demo.models.AuthenticationRequest;
import com.springboot.demo.models.AuthenticationResponse;

/**
 * <p>
 * {@link UserService} defines methods related to the 
 * user table in the database and user authentication
 * </p>
 * 
 * @author ikkesh.ramanna
 */
public interface UserService {
	
	/**
	 * <p>
	 * Generate a jwt for a user
	 * </p>
	 * 
	 * @param authRequest
	 * 				{@link AuthenticationRequest} instance
	 * 
	 * @returns a jwt to authenticate the user which username and password is
	 * 				specified in the {@link AuthenticationRequest} object argument
	 * @throws ApiRequestException
	 * 					thrown if the username or password in the {@link AuthenticationRequest} object argument
	 * 					is invalid
	 */
	public AuthenticationResponse createAuthtoken(AuthenticationRequest authRequest) throws ApiRequestException;
	
	/**
	 * <p>
	 * Listens to POST requests made to the '/user' endpoint.
	 * This endpoint is used to create a user
	 * </p>
	 * 
	 * @param authRequest
	 * 				{@link AuthenticationRequest} object
	 * 
	 * @throws ApiRequestException
	 * 					thrown if the username or password in the {@link AuthenticationRequest} object
	 * 					is taken by another user
	 */
	public void createUser(AuthenticationRequest authRequest) throws ApiRequestException;
	
}
