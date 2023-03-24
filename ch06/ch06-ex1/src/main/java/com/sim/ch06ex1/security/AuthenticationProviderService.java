package com.sim.ch06ex1.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.sim.ch06ex1.security.userdetails.CustomUserDetails;
import com.sim.ch06ex1.security.userdetails.JpaUserDetailsService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {

	private final JpaUserDetailsService userDetailsService;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final SCryptPasswordEncoder sCryptPasswordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String username = authentication.getName();
		// Credentials : 비밀번호
		final String password = authentication.getCredentials().toString();

		CustomUserDetails user = userDetailsService.loadUserByUsername(username);

		return switch (user.getUser().getAlgorithm()) {
			case BCRYPT -> checkPassword(user, password, bCryptPasswordEncoder);
			case SCRYPT -> checkPassword(user, password, sCryptPasswordEncoder);
		};

	}

	private Authentication checkPassword(CustomUserDetails user, String password, PasswordEncoder passwordEncoder) {
		if(passwordEncoder.matches(user.getPassword(), password)){
			return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
		}else {
			throw new BadCredentialsException("Bad Credentials");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
