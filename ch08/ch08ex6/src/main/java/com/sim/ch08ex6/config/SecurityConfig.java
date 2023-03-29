package com.sim.ch08ex6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		var uds = new InMemoryUserDetailsManager();

		var u1 = User.withUsername("read")
			.password("test")
			.authorities("read")
			.build();

		var u2 = User.withUsername("premium")
			.password("test")
			.authorities("read", "premium")
			.build();

		uds.createUser(u1);
		uds.createUser(u2);

		return uds;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.httpBasic();

		// 선택기로 요청을 참조할 때 특정한 규칙부터 일반적인 규칙의 순서로 지정해야 한다.
		http.authorizeRequests(
			request -> request
				.regexMatchers(".*/(us|uk|ca)+/(en|fr).*")
				.authenticated()
				.anyRequest().hasAuthority("premium")
		);

		return http.build();
	}
}
