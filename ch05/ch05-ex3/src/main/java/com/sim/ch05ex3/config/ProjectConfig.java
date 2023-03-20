package com.sim.ch05ex3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.sim.ch05ex3.security.CustomEntryPoint;

@Configuration
@EnableWebSecurity
public class ProjectConfig{

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.httpBasic(c->{
			c.realmName("OTHER");
			c.authenticationEntryPoint(new CustomEntryPoint());
			}
		);

		http.authorizeHttpRequests()
			.anyRequest()
			.authenticated();

		return http.build();
	}
}
