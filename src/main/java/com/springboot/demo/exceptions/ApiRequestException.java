package com.springboot.demo.exceptions;

import org.springframework.http.HttpStatus;

/**
 * {@link ApiRequestException} is an exception that is thrown when there are
 * errors related to API operations.
 * 
 * @author ikkesh.ramanna
 */
public class ApiRequestException extends RuntimeException {
	
	/**
	 * <p>
	 * Generated Serial ID.
	 * </p>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * <p>
	 * {@link HttpStatus} instance
	 * The http status code to return as reponse of an API request error
	 * </p>
	 */
	private HttpStatus httpStatus;
	
	/**
	 * <p>
	 * Constructor for creating an instance by providing the error desctipion.
	 * </p>
	 * 
	 * @param message
	 *            Description of the error.
	 */
	public ApiRequestException(String message) {
		super(message);
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	/**
	 * <p>
	 * Constructor for creating an instance by providing the error desctipion.
	 * </p>
	 * 
	 * @param message
	 *            Description of the error.
	 * @param httpStatus
	 * 				The http status to return with the response
	 */
	public ApiRequestException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	/* Getter */
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
	
}
