package com.sim.ch02ex5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.sim.ch02ex5.security.CustomAuthenticationProvider;

/**
 * spring security 5.7.0 이후로 WebSecurityConfigurerAdapter가 deprecated 되서
 * @EnableWebSecurity를 이용해서 설정했음
 * 아래 주소로 들어가면 @EnableWebSecurity로 변경하는 방법을 설명해줌
 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 */
@Configuration
@EnableWebSecurity
public class ProjectConfig{


	// 빈 등록을 통한 자동 설정이 가능함
	// 클래스에서 @Component 설정을 통해 Config 파일에 빈 등록을 할필요는 없음
	@Bean
	public AuthenticationProvider authenticationProvider(){
		return new CustomAuthenticationProvider();
	}


	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeHttpRequests()
			.anyRequest().authenticated();

		return http.build();
	}
}
