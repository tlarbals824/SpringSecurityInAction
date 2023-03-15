package com.sim.ch02ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProjectConfig{

	@Bean
	public UserDetailsService userDetailsService(){
		var userDetailsService = new InMemoryUserDetailsManager();

		// 주어진 사용자 이름, 암호, 권한 목록으로 사용자 생성
		var user = User.withUsername("test")
			.password("test")
			.authorities("read")
			.build();

		// UserDetailsService에서 관리하도록 사용자 추가
		userDetailsService.createUser(user);

		return userDetailsService;
	}

	// UserDetailsService 재정의하면 PasswordEncoder도 같이 정의 해줘야 함
	// 정의하지 않으면 예외가 발생함
	// java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.httpBasic();

		// 모든 요청에 대해서 인증이 필요하다.
		http.authorizeHttpRequests()
			.anyRequest().authenticated();

		// 모든 요청에 대해 인증이 필요없다.
		// http.authorizeHttpRequests()
		// 	.anyRequest().permitAll();

		return http.build();
	}
}
