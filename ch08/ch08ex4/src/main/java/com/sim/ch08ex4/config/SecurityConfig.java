package com.sim.ch08ex4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.httpBasic();

		// 선택기로 요청을 참조할 때 특정한 규칙부터 일반적인 규칙의 순서로 지정해야 한다.
		http.authorizeRequests(
			request -> request
				.mvcMatchers("/product/{code:^[0-9]*$}").permitAll()
				.anyRequest().denyAll()
		);

		return http.build();
	}
}
