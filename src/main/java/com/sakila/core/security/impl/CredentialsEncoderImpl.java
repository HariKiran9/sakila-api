/**
 * 
 */
package com.sakila.core.security.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import com.sakila.core.security.CredentialsEncoder;

/**
 * @author bc887d
 *
 */
public class CredentialsEncoderImpl extends MessageDigestPasswordEncoder implements CredentialsEncoder {

	private static final Logger logger = LoggerFactory.getLogger(CredentialsEncoderImpl.class);

	public CredentialsEncoderImpl(String algorithm) {
		super(algorithm, false);
	}

	public CredentialsEncoderImpl(String algorithm, boolean encodeHashAsBase64) throws IllegalArgumentException {
		super(algorithm, encodeHashAsBase64);
	}

	@Override
	public String encodePassword(String rawPassword) {
		logger.info("[encodePassword()] : rawPassword : {} ", rawPassword);
		return super.encodePassword(rawPassword, "SHA-1");
	}

	@Override
	public boolean isPasswordValid(String ecodedPassword, String rawPassword) {
		logger.info("[encode()] : ecodedPassword : {},  rawPassword : {} ", ecodedPassword, rawPassword);
		return super.isPasswordValid(ecodedPassword, rawPassword, null);
	}

	public String encode(CharSequence rawPassword) {
		logger.info("[encode()] : isMatch {} ", rawPassword);
		return new MessageDigestPasswordEncoder("SHA1", true).encodePassword(rawPassword.toString(), null);
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		boolean isMatch = false;
		if (rawPassword.equals(encodedPassword)) {
			isMatch = true;
		} else {
			isMatch = false;
		}
		logger.info("[matches()] : isMatch {} ", isMatch);
		return isMatch;
	}

}
