package com.sim.ch08ex5.config;

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
				// /hello, /hello/에 대해서 인증이 필요하다
				// .mvcMatchers("/hello").authenticated()
				// /hello 엔드포인트에 대해서만 인증을 요구한다. /hello/는 인증을 요구하지 않는다. -> mvcMatchers 사용하는것이 좋다.
				.antMatchers("/hello").authenticated()
		);

		return http.build();
	}
}
