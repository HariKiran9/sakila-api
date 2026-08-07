package com.sakila.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sakila.interceptor.SakilaInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration // Removed @EnableWebMvc to prevent breaking Spring Boot defaults
public class WebConfig implements WebMvcConfigurer {

	private final SakilaInterceptor sakilaInterceptor;

	// Fixed: Injecting origins from properties file to prevent hardcoded wildcards
	@Value("${app.security.allowed-origins}")
	private String[] allowedOrigins;

	@Autowired
	public WebConfig(SakilaInterceptor sakilaInterceptor) {
		this.sakilaInterceptor = sakilaInterceptor;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		log.info("... Configuring secure CORS mappings for specified origins ...");

		registry.addMapping("/**").allowedOrigins(allowedOrigins) // Fixed: Safe, restricted origins instead of "*"
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS").allowedHeaders("*")
				.allowCredentials(true); // Required for secure authorization headers / cookies
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("... Registering SakilaInterceptor workflow filter logic ...");
		registry.addInterceptor(sakilaInterceptor);
	}
}
