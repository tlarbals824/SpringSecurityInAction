package com.sim.ch10ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

import com.sim.ch10ex1.security.CsrfTokenLogger;

@EnableWebSecurity
public class ProjectConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class);

		httpSecurity.authorizeRequests(
			request -> request
				.anyRequest().permitAll()
		);

		return httpSecurity.build();
	}
}
