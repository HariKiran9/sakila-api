/**
 * 
 */
package com.sakila.core.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author bc887d
 *
 */
public interface CredentialsEncoder extends PasswordEncoder {

	public String encodePassword(String rawPassword);

	public boolean isPasswordValid(String ecodedPassword, String rawPassword);

}
