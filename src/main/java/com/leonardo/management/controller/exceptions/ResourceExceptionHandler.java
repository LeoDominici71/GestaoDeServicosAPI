package com.leonardo.management.controller.exceptions;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.leonardo.management.service.exceptions.DuplicatedEmployeeException;
import com.leonardo.management.service.exceptions.EmployeeNotFoundException;
import com.leonardo.management.service.exceptions.StandardError;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EmployeeNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Employee does not exist.");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}
	
	@ExceptionHandler(DuplicatedEmployeeException.class)
	public ResponseEntity<StandardError> duplicatedEmployee(DuplicatedEmployeeException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Employee already exist.");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<StandardError> handleMethodArgumentNotValid(NullPointerException e, HttpServletRequest request) {
	    StandardError err = new StandardError();
	    err.setTimestamp(Instant.now());
	    err.setStatus(HttpStatus.BAD_REQUEST.value());
	    err.setError("There are null fields");
	    err.setMessage(e.getMessage());
	    err.setPath(request.getRequestURI());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
	    List<String> errors = e.getBindingResult().getAllErrors().stream()
	            .map(error -> error.getDefaultMessage())
	            .collect(Collectors.toList());
	    StandardError err = new StandardError();
	    err.setTimestamp(Instant.now());
	    err.setStatus(HttpStatus.BAD_REQUEST.value());
	    err.setError("There are null fields");
	    err.setMessage(errors.toString());
	    err.setPath(request.getRequestURI());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}

