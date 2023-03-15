package com.sim.ch02ex5.security;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final String USERNAME = "test";
	private static final String PASSWORD = "test";

	// 인증 전체 논리를 나타내는 메서드
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// Principal 인터페이스의 getName() 메서드를 Authentication이 상속받는다.
		final String username = authentication.getName();
		final String password = String.valueOf(authentication.getCredentials());

		// 일반적으로 UserDetailsService, PasswordEncoder를 사용하여 인증을 처리한다.
		if(USERNAME.equals(username) && PASSWORD.equals(password)){
			return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
		} else{
			throw new AuthenticationCredentialsNotFoundException("Error in authentication");
		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return UsernamePasswordAuthenticationToken.class
			.isAssignableFrom(authenticationType);
	}
}
