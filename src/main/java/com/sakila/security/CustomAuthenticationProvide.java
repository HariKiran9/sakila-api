/**
 * 
 */
package com.sakila.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.sakila.service.StaffService;
import com.sakila.vo.StaffVO;

/**
 * @author bc887d
 *
 */
@Component
public class CustomAuthenticationProvide implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvide.class);

	@Autowired
	StaffService staffService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("... Entered into authenticate() of CustomAuthenticationProvide ...");
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		Object object = authentication.getDetails();
		logger.info(" userName : {}, password : {},  Object : {}", userName, password, object);
		if ((userName == null || userName.equals("")) && (password == null || password.equals(""))) {
			throw new AuthenticationCredentialsNotFoundException("Username or Password or empty");
		}
		String pass = toSHA1(password);
		logger.info(" Password : {}", pass);
		Authentication auth = null;
		try {
			StaffVO staff = staffService.validateUser(userName, pass);
			logger.info(" staff : {}", staff);
			List<GrantedAuthority> list = new ArrayList<>();
			auth = new UsernamePasswordAuthenticationToken(userName, password, list);
		} catch (Exception e) {
			logger.error(" Exception : ", e);
			new AuthenticationCredentialsNotFoundException("Login Failed !!!");
		}
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		logger.info(" supports : {} ", authentication);
		return true;
	}

	public static String toSHA1(String input) {
		StringBuffer sb = new StringBuffer();
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest(input.getBytes());

			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
