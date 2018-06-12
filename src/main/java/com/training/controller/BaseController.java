package com.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class BaseController {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public void notFoundError() {

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public void internalError() {

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public void badRequestError() {

	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler
	public void forbiddenError() {

	}
}
