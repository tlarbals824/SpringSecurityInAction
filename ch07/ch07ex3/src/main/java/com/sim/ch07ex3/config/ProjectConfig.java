package com.sim.ch07ex3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ProjectConfig {

	@Bean
	public UserDetailsService userDetailsService(){
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		UserDetails user1 = User.withUsername("admin")
			.password("12345")
			// .authorities("ROLE_ADMIN")
			.roles("ADMIN")
			.build();

		UserDetails user2 = User.withUsername("manager")
			.password("12345")
			// .authorities("ROLE_MANAGER")
			.roles("MANAGER")
			.build();

		manager.createUser(user1);
		manager.createUser(user2);

		return manager;
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.httpBasic();

		http.authorizeRequests(
			requests -> requests
				.anyRequest().hasRole("ADMIN")
		);

		return http.build();
	}
}
