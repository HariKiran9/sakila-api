/**
 * 
 */
package com.sakila.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sakila.interceptor.SakilaInterceptor;

/**
 * @author bc887d
 *
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(WebConfig.class);

	@Autowired
	SakilaInterceptor sakilaInterceptor;

	public void addCorsMapping(CorsRegistry registry) {
		super.addCorsMappings(registry);
		logger.info("...Entered into addCorsMapping() of WebConfig...");
		registry.addMapping("/*/**").allowedMethods("PUT", "DELETE", "POST", "GET", "OPTIONS").allowedOrigins("*")
				.allowedHeaders("*");
		registry.addMapping("/*").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedOrigins("*")
				.allowedHeaders("*");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("...Entered into addInterceptors() of WebConfig...");
		registry.addInterceptor(sakilaInterceptor);
	}

}
