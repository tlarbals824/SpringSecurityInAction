package com.sim.ch09ex3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.sim.ch09ex3.security.AuthenticationLoggingFilter;
import com.sim.ch09ex3.security.RequestValidationFilter;

@EnableWebSecurity
public class ProjectConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class);
		http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);

		http.authorizeRequests(
			request -> request.anyRequest().permitAll()
		);

		return http.build();
	}
}
