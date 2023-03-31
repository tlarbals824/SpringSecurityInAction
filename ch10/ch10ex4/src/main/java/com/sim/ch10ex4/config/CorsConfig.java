package com.sim.ch10ex4.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

	@Bean
	public CorsConfigurationSource corsConfigurationSource(){
		CorsConfiguration config = new CorsConfiguration();

		// setAllowCredentials(true)일때에는 setAllowedOrigins이 아닌 addAllowedOriginPattern을 추가해야 한다.
		// config.setAllowedOrigins(List.of("*"));
		config.addAllowedOriginPattern("*");
		config.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",config);

		return source;
	}
}
