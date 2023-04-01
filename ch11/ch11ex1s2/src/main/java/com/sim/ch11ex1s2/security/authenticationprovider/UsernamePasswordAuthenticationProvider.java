package com.sim.ch11ex1s2.security.authenticationprovider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.sim.ch11ex1s2.proxy.AuthenticationServerProxy;
import com.sim.ch11ex1s2.security.authentication.UsernamePasswordAuthentication;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	private final AuthenticationServerProxy authenticationServerProxy;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String username = authentication.getName();
		final String password = String.valueOf(authentication.getCredentials());

		authenticationServerProxy.sendAuth(username, password);

		return new UsernamePasswordAuthentication(username, password);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
	}
}
