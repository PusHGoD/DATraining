package com.training.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.training.utils.LogUtil;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	public static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		LogUtil.error(log, ex.getMessage());
		response.sendError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
	}
}
