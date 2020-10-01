package com.springboot.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.models.AuthenticationRequest;
import com.springboot.demo.services.impl.UserServiceImpl;

/**
 * <p>
 * {@link AuthenticationController} is the controller which listens to requests concerning authentication services
 * interact with.
 * </p>
 * 
 * @author ikkesh.ramanna
 */
@RestController
@CrossOrigin("http://localhost:4200") // Enable CORS for Origin http://localhost:4200 (Angular App)
public class AuthenticationController {
	
	/**
	 * <p>
	 * {@link UserServiceImpl} instance.
	 * </p>
	 */
	@Autowired
	private UserServiceImpl userService;

	/**
	 * <p>
	 * Listens to POST requests made to the '/authenticate' endpoint.
	 * This endpoint is used to generate a jwt for a user
	 * </p>
	 * 
	 * @param authRequest
	 * 				{@link AuthenticationRequest} object
	 * 
	 * @returns a jwt to authenticate the user which username and password is
	 * 				specified in the {@link AuthenticationRequest} object argument
	 * 
	 */
	@RequestMapping(path="/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateAuthtoken(@RequestBody AuthenticationRequest authRequest) {
		return ResponseEntity.ok(this.userService.createAuthtoken(authRequest));
	}
	
	/**
	 * <p>
	 * Listens to POST requests made to the '/user' endpoint.
	 * This endpoint is used to create a user
	 * </p>
	 * 
	 * @param authRequest
	 * 				{@link AuthenticationRequest} object
	 * 
	 * 
	 */
	@RequestMapping(path="/user", method = RequestMethod.POST)
	public void postUser(@RequestBody AuthenticationRequest user) {
		this.userService.createUser(user);
	}
	
	
	
}
