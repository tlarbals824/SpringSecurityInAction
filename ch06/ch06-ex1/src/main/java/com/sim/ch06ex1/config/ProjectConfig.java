package com.sim.ch06ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sim.ch06ex1.security.AuthenticationProviderService;
import com.sim.ch06ex1.security.userdetails.JpaUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ProjectConfig {

	private final JpaUserDetailsService jpaUserDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final SCryptPasswordEncoder sCryptPasswordEncoder;


	@Bean
	public AuthenticationProvider authenticationProvider(){
		return new AuthenticationProviderService(jpaUserDetailsService, bCryptPasswordEncoder, sCryptPasswordEncoder);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin()
			.defaultSuccessUrl("/main",true);

		http.authorizeHttpRequests()
			.anyRequest().authenticated();

		return http.build();
	}
}
