package com.sim.ch02ex4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * spring security 5.7.0 이후로 WebSecurityConfigurerAdapter가 deprecated 되서
 * @EnableWebSecurity를 이용해서 설정했음
 * 아래 주소로 들어가면 @EnableWebSecurity로 변경하는 방법을 설명해줌
 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 */
@Configuration
@EnableWebSecurity
public class ProjectConfig{


	// 아래 방법을 통한 통합 설정은 보다는 ex2에서 사용한 빈 등록을 통한 설정을 해야한다.
	/*
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("john")
                .password("12345")
                .authorities("read")
        .and()
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
	*/

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeHttpRequests()
			.anyRequest().authenticated();

		return http.build();
	}
}
