package com.sakila.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true) // 1. Updated method security annotation
public class SecurityConfig {

	private final CustomAuthenticationProvide customeAuthProvide;
	private final AuthenticationHandler authHandler;

	// 2. Used constructor injection instead of field @Autowired
	public SecurityConfig(CustomAuthenticationProvide customeAuthProvide, AuthenticationHandler authHandler) {
		this.customeAuthProvide = customeAuthProvide;
		this.authHandler = authHandler;
	}

	// 3. Registered the custom AuthenticationManager exposing your provider
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		log.info("...Registering CustomAuthenticationProvide...");
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(this.customeAuthProvide);
		return authenticationManagerBuilder.build();
	}

	// 4. Converted HttpSecurity configuration to a SecurityFilterChain Bean using
	// Lambda DSL
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("...Entered into filterChain(HttpSecurity) of SecurityConfig...");

		http.csrf(csrf -> csrf.disable()) // Modern lambda approach to disable CSRF
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login", "/actor", "/address", "/category", "/category/**", "/city",
								"/customer", "/film", "/inventory", "/payment", "/rental", "/staff", "/store", "/**")
						.permitAll().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/user/401").loginProcessingUrl("/login").successHandler(authHandler)
						.failureHandler(authHandler))
//				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")))
				.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())
						.httpStrictTransportSecurity(hsts -> hsts.includeSubDomains(true)));

		return http.build();
	}

	// 5. Converted WebSecurity ignoring rules into a WebSecurityCustomizer Bean
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		log.info("...Entered into webSecurityCustomizer() of SecurityConfig...");
		return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**",
				"/images/**");
	}
}