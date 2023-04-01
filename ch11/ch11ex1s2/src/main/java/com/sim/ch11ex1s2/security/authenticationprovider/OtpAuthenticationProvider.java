package com.sim.ch11ex1s2.security.authenticationprovider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.sim.ch11ex1s2.proxy.AuthenticationServerProxy;
import com.sim.ch11ex1s2.security.authentication.OtpAuthentication;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OtpAuthenticationProvider implements AuthenticationProvider {

	private final AuthenticationServerProxy authenticationServerProxy;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String username = authentication.getName();
		final String code = String.valueOf(authentication.getCredentials());

		if(authenticationServerProxy.sendOTP(username, code)) {
			return new OtpAuthentication(username, code);
		}else{
			throw new BadCredentialsException("Bad credentials");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return OtpAuthentication.class.isAssignableFrom(authentication);
	}
}
