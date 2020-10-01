package com.springboot.demo.exceptions;

import java.time.ZonedDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.demo.models.ApiException;

/**
 * {@link ApiExceptionHandler} is used to handle API related errors
 * 
 * @author ikkesh.ramanna
 */
@ControllerAdvice
public class ApiExceptionHandler {

	/**
	 * <p>
	 * Handles API Errors by sending the appropriate message and http code
	 * </p>
	 * 
	 * @param e
	 *        {@link ApiException} instance that was raised
	 */
	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
		// 1. Create payload containing exception details
		ApiException apiException = new ApiException(e.getMessage(), e.getHttpStatus(), ZonedDateTime.now());
		
		// 2. Return response entity
		return new ResponseEntity<Object>(apiException, e.getHttpStatus());
	}
}
