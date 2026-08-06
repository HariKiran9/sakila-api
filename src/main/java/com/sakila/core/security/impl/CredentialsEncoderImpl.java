package com.sakila.core.security.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sakila.core.security.CredentialsEncoder;

/**
 * Modernized password encoder implementation for Spring Boot 4.1.0. Refactored
 * from subclass inheritance to a composition-based pattern using the modern
 * Spring Crypto API.
 * 
 * @author bc887d
 */
@Component
@Deprecated
public class CredentialsEncoderImpl implements PasswordEncoder, CredentialsEncoder {

	private static final Logger logger = LoggerFactory.getLogger(CredentialsEncoderImpl.class);

	// Modern instance managing SHA-1 hashing under the hood
	@SuppressWarnings("deprecation")
	private final MessageDigestPasswordEncoder delegate = new MessageDigestPasswordEncoder("SHA-1");

	/**
	 * Default constructor satisfying modern configuration dependency injection.
	 */
	public CredentialsEncoderImpl() {
		super();
	}

	// =========================================================================
	// 1. Implementation of Modern Spring Security PasswordEncoder Interface
	// =========================================================================

	@Override
	public String encode(CharSequence rawPassword) {
		logger.info("[encode()] : Hashing raw password string.");
		if (rawPassword == null) {
			return null;
		}
		return this.delegate.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		logger.info("[matches()] : Evaluating password validation constraints.");
		if (rawPassword == null || encodedPassword == null) {
			return false;
		}

		// Maintained literal fallback matching logic from your previous snippet
		if (rawPassword.toString().equals(encodedPassword)) {
			logger.info("[matches()] : Match succeeded via raw value equivalence fallback.");
			return true;
		}

		boolean isMatch = this.delegate.matches(rawPassword, encodedPassword);
		logger.info("[matches()] : Modern structural check isMatch: {}", isMatch);
		return isMatch;
	}

	// =========================================================================
	// 2. Compatibility Hooks for Custom Legacy CredentialsEncoder Interface
	// =========================================================================

	@Override
	public String encodePassword(String rawPassword) {
		logger.info("[encodePassword()] : Forwarding legacy request invocation to encode().");
		return this.encode(rawPassword);
	}

	@Override
	public boolean isPasswordValid(String encodedPassword, String rawPassword) {
		logger.info("[isPasswordValid()] : Forwarding legacy verification invocation to matches().");
		return this.matches(rawPassword, encodedPassword);
	}
}