package com.sakila.interceptor;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SakilaInterceptor implements HandlerInterceptor {

	// Fixed: Default configuration fails gracefully if property is missing
	@Value("${CLIENT_HEADER_PREFIX:sakila}")
	private String clientPrefix;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (log.isInfoEnabled()) {
			String cleanUri = sanitizeString(request.getRequestURI());
			String cleanMethod = sanitizeString(request.getMethod());
			String cleanParams = getParameters(request);

			// Fixed: Secure, parameterized logging with sanitized variables
			log.info("[preHandle] [{}] Path: {} Parameters: {}", cleanMethod, cleanUri, cleanParams);
		}
		return true;
	}

	private String getParameters(HttpServletRequest request) {
		log.info("...Entered into getParameters() of SakilaInterceptor...");
		StringBuilder posted = new StringBuilder(); // Fixed: Use StringBuilder over synchronous StringBuffer
		Enumeration<String> e = request.getParameterNames();

		if (e != null && e.hasMoreElements()) {
			posted.append("?");
		}

		while (e != null && e.hasMoreElements()) {
			if (posted.length() > 1) {
				posted.append("&");
			}
			String curr = e.nextElement();
			String sanitizedKey = sanitizeString(curr);
			posted.append(sanitizedKey).append("=");

			// Fixed: Case-insensitive lookups for security-sensitive keywords
			String lowerKey = curr.toLowerCase();
			if (lowerKey.contains("password") || lowerKey.contains("pass") || lowerKey.contains("pwd")
					|| lowerKey.contains("token") || lowerKey.contains("secret")
					|| lowerKey.contains("authorization")) {
				posted.append("*****");
			} else {
				posted.append(sanitizeString(request.getParameter(curr)));
			}
		}

		// Fixed: Use request.getRemoteAddr(). Cloud environments should use Spring's
		// native forward headers strategy
		String ipAddr = request.getRemoteAddr();
		if (ipAddr != null && !ipAddr.isEmpty()) {
			posted.append("&_psip=").append(sanitizeString(ipAddr));
		}
		return posted.toString();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("...Entered into postHandle() of SakilaInterceptor...");
		Enumeration<String> headerNames = request.getHeaderNames();
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String headerKey = headerNames.nextElement();

				// Fixed: Check that prefix isn't null and look up securely
				if (clientPrefix != null && headerKey.toLowerCase().contains(clientPrefix.toLowerCase())) {
					String headerValue = request.getHeader(headerKey);

					// Fixed: Sanitize header injections before adding them back to response stream
					response.addHeader(sanitizeString(headerKey), sanitizeString(headerValue));
				}
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("...Entered into afterCompletion() of SakilaInterceptor...");
		// Keeps pipeline clean without tracking state changes or throwing leaks
	}

	/**
	 * Helper method to strip out dangerous CRLF line-breaks to prevent Log
	 * Injection (CWE-117)
	 */
	private String sanitizeString(String input) {
		log.info("...Entered into sanitizeString() of SakilaInterceptor...");
		if (input == null) {
			return "";
		}
		// Replace Carriage Return and Line Feed blocks to safeguard back-end aggregator
		return input.replaceAll("[\r\n]", "_");
	}
}
