package com.sakila.exception;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.sakila.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice // Intercepts exceptions thrown by all @RestController instances
public class GlobalExceptionHandler {

	/**
	 * 1. Handles Input Validation Failures (CWE-20) Triggered when incoming
	 * CategoryVO fails @NotBlank, @Size, etc.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation Failed",
				"The request body contains invalid data fields.", request.getRequestURI());
		errorResponse.setDetails(errors);

		log.warn("Validation failure recorded on endpoint {}: {}", request.getRequestURI(), errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 2. Handles Malformed URL parameters (e.g., passing characters instead of
	 * numbers for IDs)
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex,
			HttpServletRequest request) {

		String message = String.format("Parameter '%s' should be of type %s", ex.getName(),
				ex.getRequiredType().getSimpleName());

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Type Mismatch Error", message,
				request.getRequestURI());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 3. Handles Authorization Failures (CWE-285) Triggered when a user tries to
	 * access @PreAuthorize("hasRole('ROLE_ADMIN')") without permissions.
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex,
			HttpServletRequest request) {

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Forbidden Access",
				"You do not have the required permissions to perform this action.", request.getRequestURI());

		log.warn("Unauthorized security access blocked for resource: {}", request.getRequestURI());
		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}

	/**
	 * 4. Catch-All Safety Net for Unexpected Server Errors (CWE-209) Prevents
	 * internal Hibernate/SQL connection trace elements from leaking out to the user
	 * interface.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllUnexpectedExceptions(Exception ex, HttpServletRequest request) {

		// CRITICAL: Log the full internal stack trace inside your private console for
		// debugging
		log.error("Unhandled critical error intercepted on path {}", request.getRequestURI(), ex);

		// Send a generalized, completely sanitized notice back to the external client
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error", "An unexpected error occurred. Please contact the system administrator.",
				request.getRequestURI());

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
