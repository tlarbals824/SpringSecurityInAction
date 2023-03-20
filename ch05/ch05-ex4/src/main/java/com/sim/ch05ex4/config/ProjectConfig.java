package com.sim.ch05ex4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.sim.ch05ex4.security.CustomAuthenticationFailureHandler;
import com.sim.ch05ex4.security.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class ProjectConfig {

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin()
			.successHandler(customAuthenticationSuccessHandler)
			.failureHandler(customAuthenticationFailureHandler);

		http.httpBasic();

		http.authorizeHttpRequests()
			.anyRequest()
			.authenticated();

		return http.build();
	}
}
