package com.codeclause.ems.exception;

public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2582770574649784607L;

	public InvalidInputException()	{
		
	}
	
	public InvalidInputException(String message) {
		super(message);
	}
}
