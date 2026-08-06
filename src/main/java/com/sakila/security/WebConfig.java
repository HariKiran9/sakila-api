package com.sakila.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sakila.interceptor.SakilaInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer { // 1. Swapped out the removed class for the interface

	@Autowired
	private SakilaInterceptor sakilaInterceptor;

	@Override // 2. Fixed the method name to plural 'addCorsMappings' and added @Override
	public void addCorsMappings(CorsRegistry registry) {
		log.info("...Entered into addCorsMappings() of WebConfig...");

		// Note: removed super.addCorsMappings(registry) because it's a no-op on an
		// interface interface method

		registry.addMapping("/**") // 3. Simplified broad pattern mapping to standard '**'
				.allowedMethods("PUT", "DELETE", "POST", "GET", "OPTIONS", "PATCH").allowedOrigins("*")
				.allowedHeaders("*");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("...Entered into addInterceptors() of WebConfig...");
		registry.addInterceptor(sakilaInterceptor);
	}
}