package com.leonardo.management.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.leonardo.management.service.exceptions.StandardError;

public class ValidationError extends StandardError{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
