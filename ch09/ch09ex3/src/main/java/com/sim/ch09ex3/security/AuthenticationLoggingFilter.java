package com.sim.ch09ex3.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationLoggingFilter extends OncePerRequestFilter {

	private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String requestId = request.getHeader("Request-Id");

		logger.info("Successfully authenticated Request-Id: " + requestId);

		filterChain.doFilter(request, response);
	}
}
