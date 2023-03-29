package com.sim.ch08ex1.config;

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
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService(){
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		UserDetails adminUser = User.withUsername("admin")
			.password("test")
			.roles("ADMIN")
			.build();

		UserDetails managerUser = User.withUsername("manager")
			.password("test")
			.roles("MANAGER")
			.build();

		manager.createUser(adminUser);
		manager.createUser(managerUser);

		return manager;
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.httpBasic();

		// 선택기로 요청을 참조할 때 특정한 규칙부터 일반적인 규칙의 순서로 지정해야 한다.
		http.authorizeRequests(
			request -> request
				// ADMIN 역할을 가진 사용자만 접속할 수 있음
				.mvcMatchers("/hello").hasRole("ADMIN")
				// MANAGER 역할을 가진 사용자만 접속할 수 있음
				.mvcMatchers("/ciao").hasRole("MANAGER")

				// 모든 요청에 대해 인증이 없어도 엔드포인트에 접근할 수 있음
				// .anyRequest().permitAll()
				// 모든 요청에 대해 인증이 필요함
				// .anyRequest().authenticated()
				// 모든 요청에 대해 접근을 차단함
				.anyRequest().denyAll()

		);

		return http.build();
	}
}
