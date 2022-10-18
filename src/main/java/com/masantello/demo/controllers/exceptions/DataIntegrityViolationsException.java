package com.masantello.demo.controllers.exceptions;

public class DataIntegrityViolationsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityViolationsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataIntegrityViolationsException(String message) {
		super(message);
	}
	
}
