package com.codeclause.ems.exception;

public class PayrollAlreadyAddedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8783523156283590891L;

	public PayrollAlreadyAddedException(String message)	{
		super(message);
	}
}
