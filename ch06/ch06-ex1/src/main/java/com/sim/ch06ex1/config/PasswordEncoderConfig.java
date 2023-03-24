package com.sim.ch06ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * spring security 5.0 이상부터는 Password 저장할때 암호화 방식을 명시해줘야 한다.
 * ex : {bcrypt} , {scrypt} , {pbkdf2} , {sha256} 등등
 * ex : {bcrypt}+PasswordEncoder.encode(password)
 */
@Configuration
public class PasswordEncoderConfig {


	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SCryptPasswordEncoder sCryptPasswordEncoder(){
		return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
	}
}
