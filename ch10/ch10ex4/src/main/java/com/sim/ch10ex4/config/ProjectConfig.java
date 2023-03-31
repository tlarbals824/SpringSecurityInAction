package com.sim.ch10ex4.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
public class ProjectConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests(
			request -> request
				.anyRequest().permitAll()
		);

		// 설정을 같이하는것보다는 CorsConfig 파일을 만들어서 따로 관리하는것이 좋다.
		http.cors();
		// http.cors(
		// 	cors -> {
		// 		CorsConfigurationSource source = request -> {
		// 			CorsConfiguration config = new CorsConfiguration();
		// 			config.setAllowedOrigins(List.of("example.com","example.org"));
		// 			config.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
		// 			return config;
		// 		};
		// 		cors.configurationSource(source);
		// 	}
		// );



		return http.build();
	}
}
