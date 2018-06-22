package com.training.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.training.utils.LogUtil;

@RestControllerAdvice
public class BaseController {

	public static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "URL Not Found")
	public void notFoundError(NoHandlerFoundException ex) {
		LogUtil.error(log, ex.getMessage());
	}

	@ExceptionHandler(NoDataFoundException.class)
	public void notFoundDataError(NoDataFoundException ex, HttpServletResponse response) throws IOException {
		LogUtil.error(log, ex.getMessage());
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler(InternalException.class)
	public ResponseEntity<String> internalError(InternalException ex) {
		// Template
		LogUtil.error(log, ex.getMessage());
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "URL have invalid request")
	public void badRequestError(BadRequestException ex) {
		LogUtil.error(log, ex.getMessage());
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<String> forbiddenError(ForbiddenException ex) {
		// Template
		LogUtil.error(log, ex.getMessage());
		return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	}
}
