package com.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.training.exception.BadRequestException;
import com.training.exception.ForbiddenException;
import com.training.exception.InternalException;
import com.training.exception.NoDataFoundException;
import com.training.model.HttpErrorMessage;
import com.training.utils.LogUtil;

@RestControllerAdvice
public class BaseController {

	static final String HTTP_URL_NOT_FOUND_ERROR = "URL Not Found";
	static final String HTTP_DATA_NOT_FOUND_ERROR = "Data Not Found";
	static final String HTTP_INTERNAL_SERVER_ERROR = "Server got errors";
	static final String HTTP_BAD_REQUEST_ERROR = "Bad Request";
	static final String HTTP_FORBIDDEN_ERROR = "Not Authorized";

	public static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Object> notFoundError(NoHandlerFoundException ex) {
		LogUtil.error(log, ex.getMessage());
		HttpErrorMessage headers = new HttpErrorMessage(HttpStatus.NOT_FOUND.value(), HTTP_URL_NOT_FOUND_ERROR,
				ex.getMessage());
		return new ResponseEntity<Object>(headers, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<Object> notFoundDataError(NoDataFoundException ex) {
		LogUtil.error(log, ex.getMessage());
		HttpErrorMessage headers = new HttpErrorMessage(HttpStatus.NOT_FOUND.value(), HTTP_DATA_NOT_FOUND_ERROR,
				ex.getMessage());
		return new ResponseEntity<Object>(headers, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InternalException.class)
	public ResponseEntity<Object> internalError(InternalException ex) {
		LogUtil.error(log, ex.getMessage());
		HttpErrorMessage headers = new HttpErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HTTP_INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<Object>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> badRequestError(BadRequestException ex) {
		LogUtil.error(log, ex.getMessage());
		HttpErrorMessage headers = new HttpErrorMessage(HttpStatus.BAD_REQUEST.value(), HTTP_BAD_REQUEST_ERROR,
				ex.getMessage());
		return new ResponseEntity<Object>(headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<Object> forbiddenError(ForbiddenException ex) {
		LogUtil.error(log, ex.getMessage());
		HttpErrorMessage headers = new HttpErrorMessage(HttpStatus.FORBIDDEN.value(), HTTP_FORBIDDEN_ERROR,
				ex.getMessage());
		return new ResponseEntity<Object>(headers, HttpStatus.FORBIDDEN);
	}
}
