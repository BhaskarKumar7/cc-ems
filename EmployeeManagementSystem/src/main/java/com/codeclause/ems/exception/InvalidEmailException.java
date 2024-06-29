package com.codeclause.ems.exception;

public class InvalidEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3184266770371633153L;
	
	public InvalidEmailException(String message)	{
		super(message);
	}
}
