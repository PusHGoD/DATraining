package com.training.model;

import java.time.ZonedDateTime;

import com.training.utils.DateTimeUtil;

public class ErrorResponseBody {

	private ZonedDateTime timestamp;
	private int status;
	private String error;
	private String message;

	public ErrorResponseBody(int status, String error, String message) {
		this.timestamp = DateTimeUtil.getCurrent();
		this.message = message;
		this.error = error;
		this.status = status;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
