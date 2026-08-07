package com.sakila.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	private Map<String, String> details; // Used to hold field-specific validation errors

	public ErrorResponse(int status, String error, String message, String path) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
}
