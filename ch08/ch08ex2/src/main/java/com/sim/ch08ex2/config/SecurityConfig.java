package com.sim.ch08ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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

		// HTTP POST 방식으로 /a 경로를 호출할 수 있게 CSRF 비활성화
		http.csrf().disable();

		// 선택기로 요청을 참조할 때 특정한 규칙부터 일반적인 규칙의 순서로 지정해야 한다.
		http.authorizeRequests(
			request -> request
				.mvcMatchers(HttpMethod.GET, "/a").authenticated()
				.mvcMatchers(HttpMethod.POST, "/a").permitAll()
				.anyRequest().denyAll()
		);

		return http.build();
	}
}
