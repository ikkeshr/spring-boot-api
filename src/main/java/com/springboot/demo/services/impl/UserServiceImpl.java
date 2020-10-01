package com.springboot.demo.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.demo.dao.UserDao;
import com.springboot.demo.entity.User;
import com.springboot.demo.exceptions.ApiRequestException;
import com.springboot.demo.models.AuthenticationRequest;
import com.springboot.demo.models.AuthenticationResponse;
import com.springboot.demo.services.UserService;
import com.springboot.demo.utils.JwtUtil;

/**
 * <p>
 * {@link UserServiceImpl} provides implementation for the interface
 * {@link UserService} and {@link UserDetailsService} of the spring security package.
 * </p>
 * 
 * @author ikkesh.ramanna
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	
	/**
	 * <p>
	 * {@link AuthenticationManager} instance.
	 * </p>
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * <p>
	 * {@link JwtUtil} instance.
	 * </p>
	 */
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	/**
	 * <p>
	 * {@link PasswordEncoder} instance.
	 * </p>
	 */
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	/**
	 * <p>
	 * {@link UserDao} instance.
	 * </p>
	 */
	@Autowired
	private UserDao userDao;
	
	/**
	 * <p>
	 * Overrides of the {@link UserDetailsService}.
	 * It defines the authentication of a user
	 * </p>
	 * 
	 * @param username
	 * 			the username of the user to authenticate
	 * 
	 * @return
	 * 		A {@link UserDetails} instance with the user information
	 * 
	 * @throws UsernameNotFoundException
	 * 							thrown if the user does not exist on the database
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = this.userDao.findById(username);
		if (user.isPresent()) {
			return new org.springframework.security.core.userdetails.User(
					user.get().getUsername(), user.get().getPasswordEncrypted(), new ArrayList<GrantedAuthority>()
			);
		} else {
			return null;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public AuthenticationResponse createAuthtoken(AuthenticationRequest authRequest) throws ApiRequestException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
			);
		} catch(BadCredentialsException e) {
			throw new ApiRequestException("Incorrect username or password", HttpStatus.UNAUTHORIZED);
		}
		
		final UserDetails userDetails = this.loadUserByUsername(authRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return new AuthenticationResponse(jwt);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void createUser(AuthenticationRequest authRequest) throws ApiRequestException {
		
		if (this.userDao.existsById(authRequest.getUsername())) {
			throw new ApiRequestException("User already exists!", HttpStatus.CONFLICT);
		}
		
		User user = new User();
		user.setUsername(authRequest.getUsername());
		user.setPasswordEncrypted(bcryptEncoder.encode(authRequest.getPassword()));
		this.userDao.save(user);
		
	}
	
	

}
