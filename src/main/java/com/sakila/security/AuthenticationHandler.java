/**
 * 
 */
package com.sakila.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author bc887d
 *
 */
@Component
public class AuthenticationHandler implements AuthenticationFailureHandler, AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationHandler.class);

	/**
	 * 
	 */
	public AuthenticationHandler() {
		logger.info("... Entered into AuthenticationHandler() of AuthenticationHandler ...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.AuthenticationSuccessHandler#
	 * onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("... Entered into onAuthenticationSuccess() of AuthenticationHandler ...");
		response.getWriter().println("{\"success: true\"}");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.AuthenticationFailureHandler#
	 * onAuthenticationFailure(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("... Entered into onAuthenticationFailure() of AuthenticationHandler ...");
		response.getWriter().println("{\"success: false\"}");

	}

}
