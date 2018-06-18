package com.training.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.training.exception.BadRequestException;
import com.training.exception.ForbiddenException;
import com.training.exception.InternalException;
import com.training.exception.NoDataFoundException;

@RestControllerAdvice
public class BaseController {

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "URL not found")
	public ResponseEntity<String> notFoundError() {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoDataFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public void notFoundDataError(NoDataFoundException ex, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler(InternalException.class)
	public ResponseEntity<String> internalError() {
		// Template
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "URL have invalid request")
	public ResponseEntity<String> badRequestError() {
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<String> forbiddenError() {
		// Template
		return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	}
}
