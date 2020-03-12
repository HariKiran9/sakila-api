/**
 * 
 */
package com.sakila.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author bc887d
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	private CustomAuthenticationProvide customeAuthProvide;

	@Autowired
	AuthenticationHandler authHandler;

	public SecurityConfig() {
	}

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		logger.info("...Entered into configure() of SecurityConfig...");
		auth.authenticationProvider(this.customeAuthProvide);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("...Entered into configure(HttpSecurity) of SecurityConfig...");
		http.authorizeRequests()
				.antMatchers("/login", "/actor", "/address", "/category", "/category/**", "/city", "/customer", "/film",
						"/inventory", "/payment", "/rental", "/staff", "/store", "/**")
				.permitAll().antMatchers("/**").authenticated().and().formLogin().loginPage("/user/401")
				.successHandler(authHandler).failureHandler(authHandler).loginProcessingUrl("/login").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().includeSubDomains(true);
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		logger.info("...Entered into configure(WebSecurity) of SecurityConfig...");
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
