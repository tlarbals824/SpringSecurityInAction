package com.sim.ch07ex1.config;

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

		UserDetails user1 = User.withUsername("read")
			.password("12345")
			.authorities("READ")
			.build();

		UserDetails user2 = User.withUsername("write")
			.password("12345")
			.authorities("WRITE")
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
				// 모든 요청이 인증없이도 된다.
				// .anyRequest().permitAll()

				// 모든요청에 대해서 WRITE 권한이 있어야 한다.
				// .anyRequest().hasAnyAuthority("WRITE")
				.anyRequest().access("hasAuthority('WRITE')")

				// 모든 요청에 대해 WRITE, READ 권한중 하나만 있으면 된다.
				// .anyRequest().hasAnyAuthority("WRITE", "READ")
		);

		return http.build();
	}
}
