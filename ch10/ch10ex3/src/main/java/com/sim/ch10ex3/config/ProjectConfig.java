package com.sim.ch10ex3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.sim.ch10ex3.security.CustomCsrfTokenRepository;

@EnableWebSecurity
public class ProjectConfig {

	private final CustomCsrfTokenRepository customCsrfTokenRepository;

	public ProjectConfig(CustomCsrfTokenRepository customCsrfTokenRepository) {
		this.customCsrfTokenRepository = customCsrfTokenRepository;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(
			csrf -> csrf
				.csrfTokenRepository(customCsrfTokenRepository)
				.ignoringAntMatchers("/ciao")
		);

		http.authorizeRequests(
			request -> request
				.anyRequest().permitAll()
		);

		return http.build();
	}
}
