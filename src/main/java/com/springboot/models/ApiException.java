package com.springboot.models;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

/**
 * {@link ApiException} represents the structure of response
 * in case of an API error
 * 
 * @author ikkesh.ramanna
 */
public class ApiException {
	
	/**
	 * <p>
	 * The description of the error
	 * </p>
	 */
	private final String message;
	
	/**
	 * <p>
	 * The Http Status code of the response
	 * </p>
	 */
	private final HttpStatus httpStatus;
	
	/**
	 * <p>
	 * The date and time that the error occured
	 * </p>
	 */
	private final ZonedDateTime timestamp;
	
	/**
	 * <p>
	 * The constructor to initiate the instance variables
	 * </p>
	 */
	public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}

	/*
	 * Getters and Setters
	 */
	public String getMessage() {
		return message;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	
	
	
	
	
}
