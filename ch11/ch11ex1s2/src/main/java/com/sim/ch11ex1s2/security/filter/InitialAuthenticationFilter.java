package com.sim.ch11ex1s2.security.filter;

import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sim.ch11ex1s2.security.JwtProvider;
import com.sim.ch11ex1s2.security.authentication.OtpAuthentication;
import com.sim.ch11ex1s2.security.authentication.UsernamePasswordAuthentication;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitialAuthenticationFilter extends OncePerRequestFilter {

	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain){
		final String username = request.getHeader("username");
		final String password = request.getHeader("password");
		final String code = request.getHeader("code");

		if(Objects.isNull(code)){
			Authentication authentication = new UsernamePasswordAuthentication(username,
				password);
			authenticationManager.authenticate(authentication);
		} else{
			Authentication authentication = new OtpAuthentication(username, code);

			authenticationManager.authenticate(authentication);

			String jwt = jwtProvider.generateToken(username);

			response.setHeader("Authorization",jwt);
		}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return !request.getServletPath().equals("/login");
	}
}
