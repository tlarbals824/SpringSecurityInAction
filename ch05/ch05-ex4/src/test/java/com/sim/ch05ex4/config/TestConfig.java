package com.sim.ch05ex4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class TestConfig {

	@Bean
	public UserDetailsService userDetailsService(){
		InMemoryUserDetailsManager service = new InMemoryUserDetailsManager();

		UserDetails user1 = User.withUsername("write")
			.password("test")
			.authorities("write")
			.build();

		UserDetails user2 = User.withUsername("read")
			.password("test")
			.authorities("read")
			.build();

		service.createUser(user1);
		service.createUser(user2);

		return service;
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
