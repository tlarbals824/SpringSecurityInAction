package com.sim.ch11ex1s2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.sim.ch11ex1s2.security.JwtProvider;
import com.sim.ch11ex1s2.security.authenticationprovider.OtpAuthenticationProvider;
import com.sim.ch11ex1s2.security.authenticationprovider.UsernamePasswordAuthenticationProvider;
import com.sim.ch11ex1s2.security.filter.InitialAuthenticationFilter;
import com.sim.ch11ex1s2.security.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class ProjectConfig{

	private final OtpAuthenticationProvider otpAuthenticationProvider;
	private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
	private final JwtProvider jwtProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.addFilterAt(new InitialAuthenticationFilter(authenticationManager(http),jwtProvider), BasicAuthenticationFilter.class);
		http.addFilterAfter(new JwtAuthenticationFilter(jwtProvider), BasicAuthenticationFilter.class);

		http.authorizeRequests(
			request -> request
				.anyRequest().authenticated()
		);

		return http.build();
	}


	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder =
			http.getSharedObject(AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.authenticationProvider(otpAuthenticationProvider)
			.authenticationProvider(usernamePasswordAuthenticationProvider);

		return authenticationManagerBuilder.build();
	}

}
