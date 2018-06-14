package com.training.controller;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class BaseController {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> notFoundError() {
		return new ResponseEntity<>("URL not found", HttpStatus.NOT_FOUND);
	}

	// @ExceptionHandler(Throwable.class)
	// public ResponseEntity<String> internalError() {
	//
	// }
	//
	// @ExceptionHandler(Throwable.class)
	// public ResponseEntity<String> badRequestError() {
	//
	// }
	//
	// @ExceptionHandler(Throwable.class)
	// public ResponseEntity<String> forbiddenError() {
	//
	// }
}
