package com.springboot.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * <p>
 * {@link Word} represents the table <b>users</b> in the database.
 * </p>
 * 
 * @author ikkesh.ramanna
 */
@Entity
@Table(name = "users")
public class User {
	
	/**
	 * <p>
	 * primary key.
	 * the username of a user
	 * </p>
	 */
	@Id
	@Column(name = "username")
	private String username;
	
	/**
	 * <p>
	 * The encryped password of a user
	 * </p>
	 */
	@Column(name = "passwordEncrypted")
	private String passwordEncrypted;
	
	/**
	 * <p>
	 * No Argument Constructor.
	 * </p>
	 */
	public User() {}

	/**
	 * <p>
	 * Constructor to initialize of the member variables.
	 * </p>
	 */
	public User(String username, String passwordEncrypted) {
		super();
		this.username = username;
		this.passwordEncrypted = passwordEncrypted;
	}

	/*
	 * Getters and Setters
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordEncrypted() {
		return passwordEncrypted;
	}

	public void setPasswordEncrypted(String passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}
	
	

}
