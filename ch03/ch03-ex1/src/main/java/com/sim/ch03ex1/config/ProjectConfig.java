package com.sim.ch03ex1.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sim.ch03ex1.model.User;
import com.sim.ch03ex1.service.InMemoryUserDetailsService;

@Configuration
@EnableWebSecurity
public class ProjectConfig{

	@Bean
	public UserDetailsService userDetailsService(){
		UserDetails u = new User("test","test","read");
		List<UserDetails> users = List.of(u);
		return new InMemoryUserDetailsService(users);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
