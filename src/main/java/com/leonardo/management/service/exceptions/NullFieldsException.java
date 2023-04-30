package com.leonardo.management.service.exceptions;

public class NullFieldsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NullFieldsException(String message) {
		super(message);
	}
}
