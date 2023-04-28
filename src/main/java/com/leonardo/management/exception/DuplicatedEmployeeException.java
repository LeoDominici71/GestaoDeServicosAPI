package com.leonardo.management.exception;

public class DuplicatedEmployeeException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedEmployeeException(String message) {
        super(message);
    }
}