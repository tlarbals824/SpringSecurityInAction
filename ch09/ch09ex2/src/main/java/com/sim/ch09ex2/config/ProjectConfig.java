package com.sim.ch09ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.sim.ch09ex2.security.StaticKeyAuthenticationFilter;

@EnableWebSecurity
public class ProjectConfig {

	private final StaticKeyAuthenticationFilter filter;

	public ProjectConfig(StaticKeyAuthenticationFilter filter) {
		this.filter = filter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.addFilterAt(filter, BasicAuthenticationFilter.class);


		http.authorizeRequests(
			request -> request.anyRequest().permitAll()
		);

		return http.build();
	}
}
